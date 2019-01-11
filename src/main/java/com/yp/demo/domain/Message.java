package com.yp.demo.domain;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Message")
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "Message_ID")
	private Integer messageId;
	@Column(name= "AS_OF_DATE")
	private Timestamp date;
	@Column(name= "FROM_USERNAME") //TODO: CASCADE one to one relation to USER TABLE 
	private String fromUsername;
	@Column(name= "TO_USERNAME")  //TODO: one to many, change to set
	private String toUsername;
	@Column(name= "Type")  // 0: groupchat, 1: end to end
	private Integer type;
	@Column(name= "CONTENT")
	private String content;

	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Timestamp getDate() {
		return date;
	}
	public Message(Timestamp date, String fromUsername, String toUsername, Integer type, String content) {
		super();
		this.date = date;
		this.fromUsername = fromUsername;
		this.toUsername = toUsername;
		this.type = type;
		this.content = content;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	public String getToUsername() {
		return toUsername;
	}
	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Message(){}
}
