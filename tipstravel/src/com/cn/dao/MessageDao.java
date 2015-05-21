package com.cn.dao;

import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;

public interface MessageDao {

	public void addMessage(Message message);
	public void deleteMessage(int messageid);
	public void updateMessage(Message message);
	public Message loadbyid(int messageid);
	public Message loadbytime(String message_date);
	public PaginationSupport<Message> showhome(int userid,int startindex);
	public PaginationSupport<Message> showfollowing(int userid,int startindex);
}
