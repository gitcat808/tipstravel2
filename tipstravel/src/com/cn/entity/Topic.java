package com.cn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name="topic")
public class Topic {

	private int topic_id;
	private String name;
	private Set<Topic_Tag> topic_tag;
	
	public Topic() {
		topic_tag=new HashSet<Topic_Tag>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="tt_topic",cascade=CascadeType.REMOVE)
	public Set<Topic_Tag> getTopic_Tag() {
		return topic_tag;
	}

	public void setTopic_Tag(Set<Topic_Tag> topic_tag) {
		this.topic_tag = topic_tag;
	}

	@Override
	public String toString() {
		return "Topic [topic_id=" + topic_id + ", name=" + name
				+ ", topic_tag=" + topic_tag + "]";
	}

	
}
