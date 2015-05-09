package com.cn.dao;

import com.cn.entity.Like;

public interface LikeDao {
	public void add(Like like);
	public void delete(int likeid);
	public Like loadbyid(int likeid);
	public Like likeexist(int userid,int messageid);
}
