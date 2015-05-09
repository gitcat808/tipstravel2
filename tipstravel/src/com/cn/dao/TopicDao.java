package com.cn.dao;

import com.cn.entity.*;

public interface TopicDao {
	public PaginationSupport<Topic> showTopic();
	public PaginationSupport<Message> searchbyid(int topicid,int startindex);
	public PaginationSupport<Message> searchbyname(String name,int startindex);
	
}
