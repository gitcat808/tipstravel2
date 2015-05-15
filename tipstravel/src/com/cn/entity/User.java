package com.cn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "user", catalog = "tipstravel")

public class User {
	private int user_id;
	private String username;
	private String password;
	private String email;
	private Set<Like> useralllikes;
	private Set<Message> alluserMessages;
	private String message;
	private String avatar;
	private String introduction;
	private String identity;//0为普通用户1为VIP
	private String isfollowed;
	private Set<User_Following> allfollowingusermaster;//当前登陆用户关注的人
	private Set<User_Following> allfollowinguseranother;//任意一个其他用户关注的人
	
	public String getIsfollowed() {
		return isfollowed;
	}

	public void setIsfollowed(String isfollowed) {
		this.isfollowed = isfollowed;
	}

	@Column(name="identity",columnDefinition="int(11) default 0")
	@JsonIgnore
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Column(name="introduction")
	@JsonIgnore
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@OneToMany(mappedBy = "user")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	public Set<Like> getUseralllikes() {
		return useralllikes;
	}
	
	@JsonIgnore
	public void setUseralllikes(Set<Like> useralllikes) {
		this.useralllikes = useralllikes;
	}

	@OneToMany(mappedBy = "user",cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	public Set<Message> getAlluserMessages() {
		return alluserMessages;
	}

	@JsonIgnore
	public void setAlluserMessages(Set<Message> alluserMessages) {
		this.alluserMessages = alluserMessages;
	}

	public User() {
		allfollowingusermaster = new HashSet<User_Following>();
		allfollowinguseranother = new HashSet<User_Following>();
		useralllikes = new HashSet<Like>();
		alluserMessages = new HashSet<Message>();
	}

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	public Set<User_Following> getAllfollowingusermaster() {
		return allfollowingusermaster;
	}
	@JsonIgnore
	public void setAllfollowingusermaster(Set<User_Following> allfollowingusermaster) {
		this.allfollowingusermaster = allfollowingusermaster;
	}
	
	@OneToMany(mappedBy="followinguser",cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JsonIgnore
	public Set<User_Following> getAllfollowinguseranother() {
		return allfollowinguseranother;
	}
	
	@JsonIgnore
	public void setAllfollowinguseranother(Set<User_Following> allfollowinguseranother) {
		this.allfollowinguseranother = allfollowinguseranother;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="avatar",columnDefinition="vchar(255) default /image/avatar/user11.jpg")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", message="
				+ message + ", avatar=" + avatar + ", introduction="
				+ introduction + ", identity=" + identity + "]";
	}

}
