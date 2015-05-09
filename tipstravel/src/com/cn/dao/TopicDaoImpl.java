package com.cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cn.entity.*;

@Repository("topicDao")
public class TopicDaoImpl extends HibernateDaoSupport implements TopicDao {
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationSupport<Topic> showTopic() {
		List<Topic> list=this.getSession().createQuery("from Topic").setFirstResult(0).setMaxResults(20).list();
		PaginationSupport<Topic> ps=new PaginationSupport<Topic>();
		ps.setData(list);
		return ps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationSupport<Message> searchbyid(int topicid,int startindex) {
		Query query=this.getSession().createQuery("from Message as m "
				+ "where m.message_id in"
				+" (select tm.tm_message.message_id from Tag_Message as tm"
				+ " where tm.tm_tag.tag_id in"
				+ "(select tt.tt_tag.tag_id "
				+ "from Topic_Tag tt "
				+ "where tt.tt_topic.topic_id=?)"
				+ ")"
				+ " order by m.message_date DESC");
		query.setParameter(0, topicid);
		query.setFirstResult(startindex).setMaxResults(20);
		List<Message> data=query.list();
		PaginationSupport<Message> ps=new PaginationSupport<Message>();
		ps.setData(data);
		return ps;
	}

	
	@Override
	public PaginationSupport<Message> searchbyname(String name, int startindex) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationSupport<Tag> test1(int topicid) {
		Query query=this.getSession().createQuery("from Tag as t "
				+ "where t.tag_id in "
				+"(select tt.tt_tag.tag_id from Topic_Tag tt where tt.tt_topic.topic_id=?) "
				);
		query.setParameter(0, topicid);
		query.setFirstResult(0).setMaxResults(20);
		List<Tag> data=query.list();
		PaginationSupport<Tag> ps=new PaginationSupport<Tag>();
		ps.setData(data);
		return ps;
	}

	@Override
	public PaginationSupport<Tag_Message> test2(int topicid) {
		Query query=this.getSession().createQuery("from Tag_Message as tm "
				+ "where tm.tm_tag.tag_id in "
				+"(select tt.tt_tag.tag_id "
				+ "from Topic_Tag as tt "
				+ "where tt.tt_topic.topic_id=?) "
				);
		query.setParameter(0, topicid);
		List<Tag_Message> data=query.list();
		PaginationSupport<Tag_Message> ps=new PaginationSupport<Tag_Message>();
		ps.setData(data);
		return ps;
	}
	
}
