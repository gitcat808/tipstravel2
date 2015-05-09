package com.cn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.Fetchmessage_info;
import com.cn.entity.Like;
import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;
import com.cn.entity.User;
import com.cn.service.LikeService;
import com.cn.service.MessageService;
import com.cn.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private MessageService messageService;
	private UserService userService;
	private LikeService likeService;

	public LikeService getLikeService() {
		return likeService;
	}

	@Resource
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	@Resource
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	//狗！底层的方法都写好了，改一下就能用了
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addMessage()
	{
		Message message=new Message();
		message.setContext("test from add");
		message.setUser(userService.loadbyid(1));
		messageService.addMessage(message);
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value="/homepage",method=RequestMethod.POST)
	public @ResponseBody PaginationSupport showhomepage(@RequestBody Fetchmessage_info fetchmessage_info)
	{	
		PaginationSupport ps=messageService.showhome(fetchmessage_info.getUserid(),fetchmessage_info.getStartindex());
		if(ps!=null)ps.setMessage("返回成功");
		else ps.setMessage("返回失败");
		return ps;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value="/following",method=RequestMethod.POST)
	public @ResponseBody PaginationSupport showfollowing(@RequestBody Fetchmessage_info fetchmessage_info)
	{
		PaginationSupport ps=messageService.showfollowing(fetchmessage_info.getUserid(),fetchmessage_info.getStartindex());
		if(ps!=null)ps.setMessage("返回成功");
		else ps.setMessage("返回失败");
		return ps;
	}
	
	@RequestMapping(value="/like",method=RequestMethod.POST)
	public String likemessage(int userid,int messageid)
	{
		String message="用户或消息不存在";
		User userEntity=userService.loadbyid(userid);
		//System.out.println(userEntity);
		Message messageEntity=messageService.loadbyid(messageid);
		int like_count=messageEntity.getLike_count();
		//System.out.println(messageEntity);
		if(userEntity==null||messageEntity==null)
			{
				return message;
			}
		Like like_exist=likeService.likeexist(userid, messageid);
		//System.out.println("like_exist:"+like_exist);
		if(like_exist!=null)
			{
				int likeid=like_exist.getLike_id();
				messageEntity.setLike_count(--like_count);
				likeService.delete(likeid);
				messageService.updateMessage(messageEntity);
				return message="取消点赞";
			}
		else {
			Like like=new Like();
			like.setUser(userEntity);
			like.setMessage(messageEntity);
			likeService.add(like);
			messageEntity.setLike_count(++like_count);
			messageService.updateMessage(messageEntity);
			return message="添加点赞";
			}
	}
	
}
