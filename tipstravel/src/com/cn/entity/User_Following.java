package com.cn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="user_following")
//@JsonIgnoreProperties(value={"user"}) 
public class User_Following {

	private int id;
	
	private User user;
	private User followinguser;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	public User getUser() {
		return user;
	}
	@JsonBackReference
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="following_id")
//	@JsonBackReference
	public User getFollowinguser() {
		return followinguser;
	}
//	@JsonBackReference
	public void setFollowinguser(User followinguser) {
		this.followinguser = followinguser;
	}
	
}
