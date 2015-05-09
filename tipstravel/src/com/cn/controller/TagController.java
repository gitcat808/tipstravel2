package com.cn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.Fetchmessage_info;
import com.cn.entity.Message;
import com.cn.entity.Tag;
import com.cn.entity.PaginationSupport;
import com.cn.service.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {

	private TagService tagService;
	
	
	public TagService getTagService() {
		return tagService;
	}

	@Resource
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	//英文的可以搜，中文的搜不了，应该是编码问题
	@RequestMapping(value="/search" )
	public @ResponseBody PaginationSupport<Message> searchbytag(@RequestBody Fetchmessage_info fetchmessage_info)
	{
		PaginationSupport<Message> ps=new PaginationSupport<Message>();
		Tag tag=tagService.loadbyname(fetchmessage_info.getTagname());
		System.out.println(tag);
		if(tag==null) 
		{
			ps.setMessage("返回失败");
			return ps;
		}
		ps=tagService.searchbytag(tag.getTag_id(),fetchmessage_info.getStartindex());
		if(!ps.getData().iterator().hasNext())ps.setMessage("返回失败");
		else ps.setMessage("返回成功");
		return ps;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addtag(String tagname)
	{
		Tag tag=new Tag();
		tag.setTag_name(tagname);
		tagService.addTag(tag);
	}
}
