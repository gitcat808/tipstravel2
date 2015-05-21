package com.cn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="tag_message")
public class Tag_Message {

	private int id;
	private Tag tm_tag;
	private Message tm_message;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="tag_id")
	public Tag getTm_tag() {
		return tm_tag;
	}
	
	public void setTm_tag(Tag tm_tag) {
		this.tm_tag = tm_tag;
	}
	
	@ManyToOne
	@JoinColumn(name="message_id")
	@JsonIgnore
	public Message getTm_message() {
		return tm_message;
	}
	
	@JsonIgnore
	public void setTm_message(Message tm_message) {
		this.tm_message = tm_message;
	}
	public Tag_Message(Tag tm_tag, Message tm_message) {
		super();
		this.tm_tag = tm_tag;
		this.tm_message = tm_message;
	}
	
	public Tag_Message()
	{}
	
	
}
