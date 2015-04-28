package com.cn.service;

import java.util.List;

import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;

public interface MessageService {
	public void addMessage(Message message);
	public void deleteMessage(int messageid);
	public Message loadbyid(int messageid);
	public PaginationSupport showhome(int userid,int startindex);
	public PaginationSupport showfollowing(int userid,int startindex);
}
