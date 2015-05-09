package com.cn.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.UserDao;
import com.cn.entity.PaginationSupport;
import com.cn.entity.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	@Override
	public User loadbyid(int id) {
		return userDao.loadbyid(id);
	}
	@Override
	public User loadbyemail(String email) {
		return userDao.loadbyemail(email);
	}
	@Override
	public User loadbyusername(String username) {
		return userDao.loadbyusername(username);
	}
	@Override
	public PaginationSupport<User> recommendation() {
		return userDao.recommendation();
	}
	@Override
	public User login(String email) {
		User u=userDao.loadbyemail(email);
		return u;
	}

}
