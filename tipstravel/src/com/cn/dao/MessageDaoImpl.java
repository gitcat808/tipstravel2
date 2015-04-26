package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cn.entity.Message;

@Repository("messageDao")
public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void addMessage(Message message) {
		this.getHibernateTemplate().save(message);
	}

	@Override
	public void deleteMessage(int messageid) {
		Message message=this.loadbyid(messageid);
		this.getHibernateTemplate().delete(message);
	}

	@Override
	public List<Message> showhome(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> showfollowing(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message loadbyid(int messageid) {
		return this.getHibernateTemplate().load(Message.class, messageid);
	}

}
