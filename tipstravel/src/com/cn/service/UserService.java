package com.cn.service;

import java.util.List;

import com.cn.entity.User;

public interface UserService {
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public User loadbyid(int id);
	public User loadbyemail(String email);
	public User loadbyusername(String username);
	public List<User> recommendation();
	public User login(String email);
}
