package com.cn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.*;

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
	private Set<User_Following> allfollowingusermaster;//当前登陆用户关注的人
	private Set<User_Following> allfollowinguseranother;//任意一个其他用户关注的人

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Like> getUseralllikes() {
		return useralllikes;
	}

	public void setUseralllikes(Set<Like> useralllikes) {
		this.useralllikes = useralllikes;
	}

	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Message> getAlluserMessages() {
		return alluserMessages;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", password=" + password + ", email=" + email + "]";
	}

	public void setAlluserMessages(Set<Message> alluserMessages) {
		this.alluserMessages = alluserMessages;
	}

	public User() {
		allfollowingusermaster = new HashSet<User_Following>();
		allfollowinguseranother = new HashSet<User_Following>();
		useralllikes = new HashSet<Like>();
		alluserMessages = new HashSet<Message>();
	}

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<User_Following> getAllfollowingusermaster() {
		return allfollowingusermaster;
	}

	public void setAllfollowingusermaster(Set<User_Following> allfollowingusermaster) {
		this.allfollowingusermaster = allfollowingusermaster;
	}
	
	@OneToMany(mappedBy="followinguser",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<User_Following> getAllfollowinguseranother() {
		return allfollowinguseranother;
	}

	public void setAllfollowinguseranother(Set<User_Following> allfollowinguseranother) {
		this.allfollowinguseranother = allfollowinguseranother;
	}

	public User(int user_id, String username, String password, String email) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
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
	@NotEmpty(message="用户名不能为空")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	@NotEmpty(message="密码不能为空")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
	@Email(message="邮箱格式不正确")
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
