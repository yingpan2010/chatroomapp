package com.yp.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yp.demo.viewmodel.ChatInfo;
//import com.yp.demo.service.ChatService;

@ServerEndpoint(value = "/groupchat/{roomId}")
@Controller
public class GroupChat {
	// key: roomId, value: sessions
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap<>();
    //key:sessionId, value: user
    private static final Map<String, String> users = new ConcurrentHashMap<>();
    //private static final Logger logger = Logger.getLogger(GroupChant.class);
//    @Autowired
//    private ChatService chatService;
    /*
     * connect and broadcast
     */
    @OnOpen
    public void connect(@PathParam("roomId") String roomId, Session session) throws IOException {

        String name = randomId();
        if (!rooms.containsKey(roomId)) {
            Set<Session> room = new HashSet<>();
            room.add(session);
            rooms.put(roomId, room);
        } else {
            rooms.get(roomId).add(session);
        }

        users.put(session.getId(), name);
        
        // broadcast active user
        List<ChatInfo> userList = new LinkedList<>();
        rooms.get(roomId)
                .stream()
                .map(Session::getId)
                .forEach(s -> {
                	ChatInfo chatInfo = new ChatInfo();
                	chatInfo.setDate(new Timestamp(System.currentTimeMillis()));
                	chatInfo.setUsername("sys");
                	chatInfo.setChatContent(users.get(s) + "is active");
                    userList.add(chatInfo);
                });
        session.getBasicRemote().sendText(new Gson().toJson(userList));

        //broadcast the user when enter the room
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setDate(new Timestamp(System.currentTimeMillis()));
    	chatInfo.setUsername("sys");
        chatInfo.setChatContent(users.get(session.getId()) + "enter the chatting room");
        broadcast(roomId, new Gson().toJson(chatInfo));
    }

    @OnClose
    public void disConnect(@PathParam("roomId") String roomId, Session session) {
        rooms.get(roomId).remove(session);
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setDate(new Timestamp(System.currentTimeMillis()));
    	chatInfo.setUsername("sys");
        chatInfo.setChatContent(users.get(session.getId()) + "left the chat room");
        users.remove(session.getId());
        broadcast(roomId, new Gson().toJson(chatInfo));
        //logger.info("<<<<<<<<<<<<<a client has disconnected!>>>>>>>>>>>>>>");
    }

    /**
     * @param msg 
     */
    @OnMessage
    public void receiveMsg(@PathParam("roomId") String roomId,
                           String msg, Session session) {
        msg = users.get(session.getId()) + ":" + msg;
        broadcast(roomId, msg);
    }

    /**
     * send picture, file and audio
     *
     * @param name     username
     * @param roomId roomid
     * @param file     upload file
     */
//    @PostMapping("/groupchat/{roomName}/{name}")
//    public void file(@PathVariable("name") String name, @PathVariable("roomName") String roomName, MultipartFile file) {
//    	ChatInfo chatInfo = new ChatInfo();
//    	chatInfo.setDate(new Timestamp(System.currentTimeMillis()));
//    	chatInfo.setUsername(name);
//    	chatInfo.setChatContent(fileService.upload(file, roomName));
//        broadcast(roomName, new Gson().toJson(chatInfo));
//    }

    private void broadcast(String roomId, String msg) {
        rooms.get(roomId).forEach(s -> {
            //s.getBasicRemote().sendText(msg);
			s.getAsyncRemote().sendText(msg);
        });
        
    }

    private String randomId() {
        Random random = new Random();
        String str = "";
        int hightPos, lowPos;
        for (int i = 0; i < 4; ++i) {
            hightPos = (176 + Math.abs(random.nextInt(39)));
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos)).byteValue();
            b[1] = (Integer.valueOf(lowPos)).byteValue();
            str += new String(b);
        }
        return str;
    }

}
