package com.cn.controller;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;
import com.cn.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private MessageService messageService;

	public MessageService getMessageService() {
		return messageService;
	}

	@Resource
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping("/homepage")
	public void showhomepage()
	{
		//System.out.println("enter homepage");
		int id=6;
		int index=0;
		PaginationSupport ps=messageService.showhome(id, index);
		if(ps!=null)ps.setMessage("返回成功");

		System.out.println(ps);
		Iterator iterator=ps.getData().iterator();
		while(iterator.hasNext())
		{
			Message message=(Message)iterator.next();
			System.out.println(message);
		}
//		return ps;
	}
	
	@RequestMapping("/following")
	public void showfollowing()
	{
		int id=1;int startindex=0;
		PaginationSupport ps=messageService.showfollowing(id, startindex);
		if(ps!=null)ps.setMessage("返回成功");
		else ps.setMessage("返回失败");
		System.out.println(ps);
		Iterator iterator=ps.getData().iterator();
		while(iterator.hasNext())
		{
			Message message=(Message)iterator.next();
			System.out.println(message);
		}
	}
}
