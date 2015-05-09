package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.LikeDao;
import com.cn.entity.Like;

@Service("likeService")
public class LikeServiceImpl implements LikeService {

	private LikeDao likeDao;
	
	public LikeDao getLikeDao() {
		return likeDao;
	}

	@Resource
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	@Override
	public void add(Like like) {
		likeDao.add(like);
	}

	@Override
	public Like likeexist(int userid, int messageid) {
		return likeDao.likeexist(userid, messageid);
	}

	@Override
	public void delete(int likeid) {
		likeDao.delete(likeid);
	}

	@Override
	public Like loadbyid(int likeid) {
		return likeDao.loadbyid(likeid);
	}
}
