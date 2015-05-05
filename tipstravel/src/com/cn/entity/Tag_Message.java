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
	private Tag tag;
	private Message message;
	
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
	public Tag getTag() {
		return tag;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	@ManyToOne
	@JoinColumn(name="message_id")
	@JsonIgnore
	public Message getMessage() {
		return message;
	}
	
	@JsonIgnore
	public void setMessage(Message message) {
		this.message = message;
	}
	
}
