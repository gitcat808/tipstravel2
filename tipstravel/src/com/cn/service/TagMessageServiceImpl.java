package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.TagMessageDao;
import com.cn.entity.Tag_Message;

@Service("tagMessageService")
public class TagMessageServiceImpl implements TagMessageService {

	private TagMessageDao tagMessageDao;
	
	public TagMessageDao getTagMessageDao() {
		return tagMessageDao;
	}

	@Resource
	public void setTagMessageDao(TagMessageDao tagMessageDao) {
		this.tagMessageDao = tagMessageDao;
	}



	@Override
	public void addTagMessage(Tag_Message tag_Message) {
		tagMessageDao.addTagMessage(tag_Message);
		
	}

}
