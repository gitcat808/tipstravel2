package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;

@Repository("messageDao")
public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void addMessage(Message message) {
		this.getHibernateTemplate().save(message);
	}

	@Override
	@Transactional
	public void deleteMessage(int messageid) {
		Message message=this.loadbyid(messageid);
		this.getHibernateTemplate().delete(message);
	}

	@Override
	public PaginationSupport<Message> showhome(int userid,int startindex) {
		Query query=this.getSession().createQuery("from Message as m where m.user.user_id=? order by m.message_date DESC");
		query.setParameter(0, userid);
		query.setFirstResult(startindex).setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<Message> data=query.list();
		PaginationSupport<Message> ps=new PaginationSupport<Message>();
		ps.setData(data);
		return ps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationSupport<Message> showfollowing(int userid,int startindex) {
		Query query=this.getSession().createQuery("from Message as m"
				+ " where m.user.user_id in"
				+" (select uf.followinguser.user_id from User_Following as uf"
				+ " where uf.user.user_id=?)"
				+ " order by m.message_date DESC");
		query.setParameter(0, userid);
		query.setFirstResult(startindex).setMaxResults(10);
		List<Message> data=query.list();
		PaginationSupport<Message> ps=new PaginationSupport<Message>();
		ps.setData(data);
		return ps;
	}

	@Override
	public Message loadbyid(int messageid) {
		return this.getHibernateTemplate().load(Message.class, messageid);
	}

	@Override
	@Transactional
	public void updateMessage(Message message) {
		this.getHibernateTemplate().update(message);
	}

}
