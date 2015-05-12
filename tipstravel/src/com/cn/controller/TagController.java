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

import com.cn.entity.Fetchmessage_info;
import com.cn.entity.Like;
import com.cn.entity.Message;
import com.cn.entity.Tag;
import com.cn.entity.PaginationSupport;
import com.cn.entity.User_Following;
import com.cn.service.LikeService;
import com.cn.service.TagService;
import com.cn.service.UserFollowingService;

@Controller
@RequestMapping("/tag")
public class TagController {

	private TagService tagService;
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

	public TagService getTagService() {
		return tagService;
	}

	@Resource
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/search" )
	public @ResponseBody PaginationSupport<Message> searchbytag(@RequestBody Fetchmessage_info fetchmessage_info)
	{
		PaginationSupport<Message> ps=new PaginationSupport<Message>();
		Tag tag=tagService.loadbyname(fetchmessage_info.getTagname());
		int userid=fetchmessage_info.getUserid();
		if(tag==null) 
		{
			ps.setMessage("返回失败");
			return ps;
		}
		ps=tagService.searchbytag(tag.getTag_id(),fetchmessage_info.getStartindex());
		if(!ps.getData().iterator().hasNext())ps.setMessage("返回失败");
		else ps.setMessage("返回成功");
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
				message.getUser().setIsfollowed("关注对象为自身");
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
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addtag(String tagname)
	{
		Tag tag=new Tag();
		tag.setTag_name(tagname);
		tagService.addTag(tag);
	}
}
