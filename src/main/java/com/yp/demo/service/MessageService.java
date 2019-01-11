package com.yp.demo.service;

import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yp.demo.domain.Message;
import com.yp.demo.repository.MessageDao;


@Service(value = "MessageService")
public class MessageService {
	//private static final Logger logger = Logger.getLogger(MessageService.class);
	@Autowired
	private MessageDao messageDao;

	@Transactional
	public List<Message> findByUsername(String username) {
		return messageDao.findByUsername(username);
	}
}
