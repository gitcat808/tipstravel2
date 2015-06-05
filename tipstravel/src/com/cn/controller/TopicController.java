package com.cn.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.*;
import com.cn.service.LikeService;
import com.cn.service.TopicService;
import com.cn.service.UserFollowingService;


@Controller
@RequestMapping("/topic")
public class TopicController {

	private TopicService topicService;
	private LikeService likeService;
	private UserFollowingService userFollowingService;
	
	

	public UserFollowingService getUserFollowingService() {
		return userFollowingService;
	}

	@Resource
	public void setUserFollowingService(UserFollowingService userFollowingService) {
		this.userFollowingService = userFollowingService;
	}

	public LikeService getLikeService() {
		return likeService;
	}

	@Resource
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public TopicService getTopicService() {
		return topicService;
	}

	@Resource
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@RequestMapping(value="/show")
	public @ResponseBody PaginationSupport<Topic> showTopic()
	{
		PaginationSupport<Topic> ps=topicService.showTopic();
		if(!ps.getData().iterator().hasNext())ps.setMessage("返回失败");
		else ps.setMessage("返回成功");
		return ps;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public @ResponseBody PaginationSupport<Message> searchbyid(@RequestBody Fetchmessage_info fetchmessage_info)
	{
		int userid=fetchmessage_info.getUserid();
		PaginationSupport<Message> ps=topicService.searchbyid(fetchmessage_info.getTopicid(), fetchmessage_info.getStartindex());
		if(!ps.getData().iterator().hasNext())ps.setMessage("fail");
		else ps.setMessage("success");
		List<Message> data=new ArrayList<Message>();
		Iterator iterator=ps.getData().iterator();
		int size=ps.getData().size();
		for(int i=0;i<size;i++)
		{
			Message message=(Message)iterator.next();
			int messageid=message.getMessage_id();
			int followingid=message.getUser().getUser_id();
			Like like_exist=likeService.likeexist(userid, messageid);
			if(like_exist!=null) message.setIsliked("true");
			else message.setIsliked("false");
			if(userid==followingid)
			{
				message.getUser().setIsfollowed("itself");
			}
			else
			{
				User_Following following_exist=userFollowingService.followexist(userid, followingid);
				if(following_exist!=null)message.getUser().setIsfollowed("true");
				else message.getUser().setIsfollowed("false");
			}
			data.add(message);
		}
		ps.setData(data);
		return ps;
	}
	

	
}
