package com.cn.dao;

import com.cn.entity.*;

public interface UserFollowingDao {
	public void follow(User_Following user_Following);
	public void unfollow(User_Following user_Following);
	public User_Following followexist(int userid,int followingid);
}
