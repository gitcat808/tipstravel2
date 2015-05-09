package com.cn.dao;

import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;

public interface MessageDao {

	public void addMessage(Message message);
	public void deleteMessage(int messageid);
	public void updateMessage(Message message);
	public Message loadbyid(int messageid);
	public PaginationSupport showhome(int userid,int startindex);
	public PaginationSupport showfollowing(int userid,int startindex);
}
