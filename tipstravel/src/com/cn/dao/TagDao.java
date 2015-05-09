package com.cn.dao;


import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;
import com.cn.entity.Tag;

public interface TagDao {

	public void addTag(Tag tag);
	public void deleteTag(int tagid);
	public Tag loadbyid(int tagid); 
	public PaginationSupport<Message> searchbytag(int tagid, int startindex);
	public Tag loadbyname(String tagname);
}
