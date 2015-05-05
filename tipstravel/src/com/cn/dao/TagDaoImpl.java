package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;
import com.cn.entity.Tag;

@Repository("tagDao")
public class TagDaoImpl extends HibernateDaoSupport implements TagDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void addTag(Tag tag) {
		this.getHibernateTemplate().save(tag);
	}

	@Override
	public void deleteTag(int tagid) {
		Tag tag=this.loadbyid(tagid);
		this.getHibernateTemplate().delete(tag);
	}

	@Override
	public Tag loadbyid(int tagid) {
		return this.getHibernateTemplate().load(Tag.class, tagid);
	}

	@Override
	public PaginationSupport searchbytag(String tagname, int startindex) {
		Query query=this.getSession().createQuery("from Message as m"
				+ " where m.message_id in"
				+" (select tm.message_id from Tag_Message as tm"
				+ " where tm.tag_name=?)"
				+ " order by m.message_date DESC");
		query.setParameter(0, tagname);
		query.setFirstResult(startindex).setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<Message> data=query.list();
		PaginationSupport ps=new PaginationSupport();
		ps.setData(data);
		return ps;
	}

	@Override
	public Tag loadbyname(String tagname) {
		return (Tag)this.getSession().createQuery("from Tag where tag_name=?")
				.setParameter(0, tagname).uniqueResult();
	}

}
