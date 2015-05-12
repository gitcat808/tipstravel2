package com.cn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_following")
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
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="following_id")
	public User getFollowinguser() {
		return followinguser;
	}
	
	public void setFollowinguser(User followinguser) {
		this.followinguser = followinguser;
	}
	
	public User_Following(User user, User followinguser) {
		super();
		this.user = user;
		this.followinguser = followinguser;
	}
	
	public User_Following()
	{
		
	}
	
}
