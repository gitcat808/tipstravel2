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
		Query query=this.getSession().createQuery(
				"from Message as m where m.message_id in"
				+" (select tm.tm_message.message_id from Tag_Message tm"
				+ " where tm.tm_tag.tag_id in"
				+ "(select tt.tt_tag.tag_id from Topic_Tag tt where tt.tt_topic.topic_id=?)"
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
	
}
