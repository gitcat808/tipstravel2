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
import com.cn.entity.Tag;

@Repository("tagDao")
public class TagDaoImpl extends HibernateDaoSupport implements TagDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	@Transactional
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

	@SuppressWarnings("unchecked")
	@Override
	public PaginationSupport<Message> searchbytag(int tagid, int startindex) {
		Query query=this.getSession().createQuery("from Message as m"
				+ " where m.message_id in"
				+" (select tm.tm_message.message_id from Tag_Message as tm"
				+ " where tm.tm_tag.tag_id=?)"
				+ " order by m.message_date DESC");
		query.setParameter(0, tagid);
		query.setFirstResult(startindex).setMaxResults(20);
		List<Message> data=query.list();
		PaginationSupport<Message> ps=new PaginationSupport<Message>();
		ps.setData(data);
		return ps;
	}

	@Override
	public Tag loadbyname(String tagname) {
		return (Tag)this.getSession().createQuery("from Tag where tag_name=?")
				.setParameter(0, tagname).uniqueResult();
	}

}
