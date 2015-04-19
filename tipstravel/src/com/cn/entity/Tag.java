package com.cn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="tag")
public class Tag {
	private int tag_id;
	private String tag_name;
	private Set<Message> alltagmessages;
	
	public Tag(){
		alltagmessages=new HashSet<Message>();
	}
	
	@OneToMany(mappedBy="tag",cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.EXTRA) //LazyCollection属性设置成EXTRA指定了当如果查询数据的个数时候，只会发出一条 count(*)的语句，提高性能
	public Set<Message> getAlltagmessages() {
		return alltagmessages;
	}
	public void setAlltagmessages(Set<Message> alltagmessages) {
		this.alltagmessages = alltagmessages;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tag_id")
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	
	@Column(name="tag_name")
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	
		
	

}
