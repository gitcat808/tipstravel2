package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.MessageDao;
import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao;
	
	public MessageDao getMessageDao() {
		return messageDao;
	}

	@Resource
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public void addMessage(Message message) {
		messageDao.addMessage(message);
	}

	@Override
	public void deleteMessage(int messageid) {
		messageDao.deleteMessage(messageid);
	}

	@Override
	public Message loadbyid(int messageid) {
		return messageDao.loadbyid(messageid);
	}

	@Override
	public PaginationSupport<Message> showhome(int userid,int startindex) {
		return messageDao.showhome(userid, startindex);
	}

	@Override
	public PaginationSupport<Message> showfollowing(int userid,int startindex) {
		return messageDao.showfollowing(userid, startindex);
	}

	@Override
	public void updateMessage(Message message) {
		messageDao.updateMessage(message);
	}

	@Override
	public Message loadbydate(String message_date) {
		return messageDao.loadbytime(message_date);
	}

}
