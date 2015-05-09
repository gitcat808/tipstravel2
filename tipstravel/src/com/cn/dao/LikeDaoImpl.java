package com.cn.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cn.entity.Like;

@Repository("likeDao")
public class LikeDaoImpl extends HibernateDaoSupport implements LikeDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void add(Like like) {
		this.getHibernateTemplate().save(like);
	}

	@Override
	public Like likeexist(int userid, int messageid) {
		return (Like)this.getSession().createQuery("from Like where user_id=? and message_id=?")
				.setParameter(0, userid).setParameter(1,messageid).uniqueResult();
	}

	@Override
	@Transactional
	public void delete(int likeid) {
		Like like=this.loadbyid(likeid);
		try {
			this.getHibernateTemplate().delete(like);
		} catch (Exception e) {
			System.out.println("³ö´í");
		}
		
	}

	@Override
	public Like loadbyid(int likeid) {
		return (Like)this.getHibernateTemplate().load(Like.class, likeid);
	}

	

	

}
