package com.cn.dao;

import com.cn.entity.User_Following;

public interface UserFollowingDao {
	public void follow(User_Following user_Following);
	public void unfollow(User_Following user_Following);
}
