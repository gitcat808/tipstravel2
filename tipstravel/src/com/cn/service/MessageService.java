package com.cn.service;


import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;

public interface MessageService {
	public void addMessage(Message message);
	public void deleteMessage(int messageid);
	public void updateMessage(Message message);
	public Message loadbyid(int messageid);
	public PaginationSupport<Message> showhome(int userid,int startindex);
	public PaginationSupport<Message> showfollowing(int userid,int startindex);
	public Message loadbydate(String message_date);

}
