package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.UserFollowingDao;
import com.cn.entity.User_Following;

@Service("userfollowingService")
public class UserFollowingServiceImpl implements UserFollowingService {

	private UserFollowingDao userFollowingDao;
	
	public UserFollowingDao getUserFollowingDao() {
		return userFollowingDao;
	}
	@Resource
	public void setUserFollowingDao(UserFollowingDao userFollowingDao) {
		this.userFollowingDao = userFollowingDao;
	}

	@Override
	public void follow(User_Following user_Following) {
		userFollowingDao.follow(user_Following);
	}

	@Override
	public void unfollow(User_Following user_Following) {
		userFollowingDao.unfollow(user_Following);
	}

}
