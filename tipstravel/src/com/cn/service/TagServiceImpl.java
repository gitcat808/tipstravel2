package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.dao.TagDao;
import com.cn.entity.PaginationSupport;
import com.cn.entity.Tag;

@Service("tagService")
public class TagServiceImpl implements TagService {

	private TagDao tagDao;
	
	public TagDao getTagDao() {
		return tagDao;
	}

	@Resource
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	@Override
	public void addTag(Tag tag) {
		tagDao.addTag(tag);
	}

	@Override
	public void deleteTag(int tagid) {
		tagDao.deleteTag(tagid);
	}

	@Override
	public Tag loadbyid(int tagid) {
		return tagDao.loadbyid(tagid);
	}

	@Override
	public PaginationSupport searchbytag(String tagname, int startindex) {
		return tagDao.searchbytag(tagname, startindex);
	}

}
