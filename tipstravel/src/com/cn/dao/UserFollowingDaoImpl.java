package com.cn.dao;

import javax.annotation.*;

import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cn.entity.*;

@Repository("userfollowingDao")
public class UserFollowingDaoImpl extends HibernateDaoSupport implements UserFollowingDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void follow(User_Following user_Following) {
		this.getHibernateTemplate().save(user_Following);
		
	}

	@Override
	@Transactional
	public void unfollow(User_Following user_Following) {
		this.getHibernateTemplate().delete(user_Following);
	}

	@Override
	public User_Following followexist(int userid, int followingid) {
		return (User_Following)this.getSession().createQuery("from User_Following where user_id=? and following_id=?")
				.setParameter(0, userid).setParameter(1,followingid).uniqueResult();
	}
	

}
