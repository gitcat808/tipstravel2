package com.cn.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.TopicDao;
import com.cn.entity.*;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

	private TopicDao topicDao;
	
	
	public TopicDao getTopicDao() {
		return topicDao;
	}

	@Resource
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}


	@Override
	public PaginationSupport<Topic> showTopic() {
		return topicDao.showTopic();
	}

	@Override
	public PaginationSupport<Message> searchbyid(int topicid, int startindex) {
		return topicDao.searchbyid(topicid, startindex);
	}

	@Override
	public PaginationSupport<Tag> test1(int topicid) {
		return topicDao.test1(topicid);
	}

	@Override
	public PaginationSupport<Tag_Message> test2(int topicid) {
		return topicDao.test2(topicid);
	}

	@Override
	public PaginationSupport<Message> searchbyname(String name, int startindex) {
		return topicDao.searchbyname(name, startindex);
	}

}
