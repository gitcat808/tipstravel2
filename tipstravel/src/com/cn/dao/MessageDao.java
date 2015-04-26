package com.cn.dao;

import java.util.List;

import com.cn.entity.Message;

public interface MessageDao {

	public void addMessage(Message message);
	public void deleteMessage(int messageid);
	public Message loadbyid(int messageid);
	public List<Message> showhome(int userid);
	public List<Message> showfollowing(int userid);
}
