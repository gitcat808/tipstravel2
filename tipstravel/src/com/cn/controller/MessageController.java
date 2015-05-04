package com.cn.controller;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.Fetchmessage_info;
import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;
import com.cn.entity.User;
import com.cn.entity.Fetchmessage_info;
import com.cn.service.MessageService;
import com.cn.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private MessageService messageService;
	private UserService userService;

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
	
	@RequestMapping(value="/homepage",method=RequestMethod.POST)
	public @ResponseBody PaginationSupport showhomepage(@RequestBody Fetchmessage_info fetchmessage_info)
	{	
		//System.out.println("lalallalaalla");
//		System.out.println(fetchmessage_info.getUserid());
//		System.out.println(fetchmessage_info.getStartindex());
		PaginationSupport ps=messageService.showhome(fetchmessage_info.getUserid(),fetchmessage_info.getStartindex());
		
		if(ps!=null)ps.setMessage("返回成功");
		else ps.setMessage("返回失败");
		Iterator iterator=ps.getData().iterator();
		while(iterator.hasNext())
		{
			Message message=(Message)iterator.next();
			System.out.println(message);
		}
//		System.out.println("enddddd");
		return ps;
	}
	
	@RequestMapping(value="/following",method=RequestMethod.POST)
	public @ResponseBody PaginationSupport showfollowing(@RequestBody Fetchmessage_info fetchmessage_info)
	{
		PaginationSupport ps=messageService.showfollowing(fetchmessage_info.getUserid(),fetchmessage_info.getStartindex());
		if(ps!=null)ps.setMessage("返回成功");
		else ps.setMessage("返回失败");
//		System.out.println(ps);
//		Iterator iterator=ps.getData().iterator();
//		while(iterator.hasNext())
//		{
//			Message message=(Message)iterator.next();
//			System.out.println(message);
//		}
		return ps;
	}
}
