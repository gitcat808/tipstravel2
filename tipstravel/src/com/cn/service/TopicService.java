package com.cn.service;

import com.cn.entity.*;

public interface TopicService {
	public PaginationSupport<Topic> showTopic();
	public PaginationSupport<Message> searchbyid(int topicid,int startindex);
}
