package com.cn.dao;


import com.cn.entity.PaginationSupport;
import com.cn.entity.User;

public interface UserDao {

	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public User loadbyid(int id);
	public User loadbyemail(String email);
	public User loadbyusername(String username);
	public PaginationSupport<User> recommendation();
}
