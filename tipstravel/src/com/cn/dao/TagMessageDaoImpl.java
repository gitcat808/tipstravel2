package com.cn.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cn.entity.Tag_Message;

@Repository("tagMessageDao")
public class TagMessageDaoImpl extends HibernateDaoSupport implements TagMessageDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	@Transactional
	public void addTagMessage(Tag_Message tag_Message) {
		this.getHibernateTemplate().save(tag_Message);
	}

}
