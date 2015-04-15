package com.cn.controller;



import javax.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.entity.User;
import com.cn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/test")
	public String test(Model model)
	{
		model.addAttribute(new User());
		System.out.println("enter test to user/add.jsp");
		return "user/add";
	}

	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addUser(@Validated User user,BindingResult br)
	{
		if(br.hasErrors()) return "user/add";
		userService.addUser(user);
		return "user/test";
	}
	
}
