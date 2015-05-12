package com.cn.service;

import com.cn.entity.User_Following;

public interface UserFollowingService {
	public void follow(User_Following user_Following);
	public void unfollow(User_Following user_Following);
	public User_Following followexist(int userid,int followingid);
}
