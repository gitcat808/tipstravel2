package com.cn.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.*;
import com.cn.service.TopicService;


@Controller
@RequestMapping("/topic")
public class TopicController {

	private TopicService topicService;

	public TopicService getTopicService() {
		return topicService;
	}

	@Resource
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value="/show")
	public PaginationSupport<Topic> showTopic()
	{
		PaginationSupport<Topic> ps=topicService.showTopic();
		if (ps!=null) ps.setMessage("返回成功");
		else ps.setMessage("返回失败");
		System.out.println(ps);
		return ps;
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public @ResponseBody PaginationSupport<Message> searchbyid(@RequestBody Fetchmessage_info fetchmessage_info)
	{
		PaginationSupport<Message> ps=topicService.searchbyid(fetchmessage_info.getTopicid(), fetchmessage_info.getStartindex());
		if(!ps.getData().iterator().hasNext())ps.setMessage("返回失败");
		else ps.setMessage("返回成功");
		return ps;
	}
	
}
