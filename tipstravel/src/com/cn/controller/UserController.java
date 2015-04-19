package com.cn.controller;



import javax.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody User addUser(@RequestBody User user)
	{
//		if(br.hasErrors()) //return "user/add";
//		{
//			user.setMessage("注册不成功");
//			return user;
//		}
		System.out.println("come");
		User user_load_by_email=userService.loadbyemail(user.getEmail());
		if(user_load_by_email!=null)
		{
			user_load_by_email.setMessage("邮箱已经注册");
			return user_load_by_email;
		}
		User user_load_by_username=userService.loadbyusername(user.getUsername());
		if(user_load_by_username!=null)
		{
			user_load_by_username.setMessage("用户名已注册");
			return user_load_by_username;
		}
		userService.addUser(user);
		user.setMessage("注册成功");
		return user;
	}
	
}
