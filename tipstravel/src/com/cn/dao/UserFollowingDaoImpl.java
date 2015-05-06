package com.cn.dao;

import javax.annotation.*;

import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cn.entity.User_Following;

@Repository("userfollowingDao")
public class UserFollowingDaoImpl extends HibernateDaoSupport implements UserFollowingDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void follow(User_Following user_Following) {
		this.getHibernateTemplate().save(user_Following);
		
	}

	@Override
	public void unfollow(User_Following user_Following) {
		this.getHibernateTemplate().delete(user_Following);
	}
	

}
