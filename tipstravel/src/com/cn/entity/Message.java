package com.cn.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="message")
public class Message {
	private int message_id;
	private String context;
	private String image;
	private Date message_date;
	private Tag tag;
	private User user;
	private Set<Like> messagealllikes;
	
	public Message()
	{
		messagealllikes=new HashSet<Like>();
	}
	
	@OneToMany(mappedBy="message",cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Like> getMessagealllikes() {
		return messagealllikes;
	}
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
	
	@ManyToOne// ManyToOne指定了多对一的关系，fetch=FetchType.LAZY属性表示在多的那一方通过延迟加载的方式加载对象(默认不是延迟加载)
	@JoinColumn(name="tag_id")
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
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
	@Temporal(TemporalType.TIME)
	public Date getMessage_date() {
		return message_date;
	}
	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}

	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", context=" + context
				+ ", image=" + image + ", message_date=" + message_date
				+ ", tag=" + tag + ", user=" + user + ", messagealllikes="
				+ messagealllikes + "]";
	}
	
		
}
