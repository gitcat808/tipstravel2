package com.cn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="message")
public class Message {
	private int message_id;
	private String context;
	private String image;
	private String message_date;
	private Set<Tag_Message> tag_message;
	private User user;
	private Set<Like> messagealllikes;
	private int like_count;
	private String isliked;
	
	public String getIsliked() {
		return isliked;
	}

	public void setIsliked(String isliked) {
		this.isliked = isliked;
	}

	public Message()
	{
		messagealllikes=new HashSet<Like>();
		tag_message=new HashSet<Tag_Message>();
	}

	@OneToMany(mappedBy="tm_message",cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Tag_Message> getTag_message() {
		return tag_message;
	}
	
	public void setTag_message(Set<Tag_Message> tag_message) {
		this.tag_message = tag_message;
	}

	@OneToMany(mappedBy="message")
	@JsonIgnore
	public Set<Like> getMessagealllikes() {
		return messagealllikes;
	}
	
	@JsonIgnore
	public void setMessagealllikes(Set<Like> messagealllikes) {
		this.messagealllikes = messagealllikes;
	}
	
	@ManyToOne // ManyToOne指定了多对一的关系，fetch=FetchType.LAZY属性表示在多的那一方通过延迟加载的方式加载对象(默认不是延迟加载)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	
	@Column(name="context")
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Column(name="image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(name="message_date")
	public String getMessage_date() {
		return message_date;
	}
	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}

	@Column(name="like_count",columnDefinition="int(20) default 0")
	public int getLike_count() {
		return like_count;
	}


	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}


	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", context=" + context
				+ ", image=" + image + ", message_date=" + message_date
				+ ", tag_message=" + tag_message + ", user=" + user
				+ ", like_count=" + like_count + "]";
	}



}
