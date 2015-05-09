package com.cn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="topic_tag")
public class Topic_Tag {
	private int id;
	private Topic tt_topic;
	private Tag tt_tag;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	public Topic getTt_topic() {
		return tt_topic;
	}
	public void setTt_topic(Topic tt_topic) {
		this.tt_topic = tt_topic;
	}
	
	@ManyToOne
	@JoinColumn(name="tag_id")
	public Tag getTt_tag() {
		return tt_tag;
	}
	public void setTt_tag(Tag tt_tag) {
		this.tt_tag = tt_tag;
	}
	@Override
	public String toString() {
		return "Topic_Tag [id=" + id + ", tt_topic=" + tt_topic + ", tt_tag="
				+ tt_tag + "]";
	}
	
}
