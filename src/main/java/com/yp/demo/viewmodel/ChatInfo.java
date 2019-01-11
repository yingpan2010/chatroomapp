package com.yp.demo.viewmodel;

import java.sql.Timestamp;

public class ChatInfo {
	private Timestamp date;
	private String username;
	private String chatContent;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public ChatInfo(){}
	public ChatInfo(Timestamp date, String username, String chatContent) {
		super();
		this.date = date;
		this.username = username;
		this.chatContent = chatContent;
	}

	@Override
	public String toString() {
		return "ChatInfo [date=" + date + ", username=" + username + ", chatContent=" + chatContent + "]";
	}
}
