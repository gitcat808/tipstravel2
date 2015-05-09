package com.cn.service;

import com.cn.entity.Like;

public interface LikeService {
	public void add(Like like);
	public void delete(int likeid);
	public Like likeexist(int userid,int messageid);
	public Like loadbyid(int likeid);
}
