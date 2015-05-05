package com.cn.controller;



import javax.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.User;
import com.cn.entity.User_Following;
import com.cn.service.UserFollowingService;
import com.cn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	private UserFollowingService userFollowingService;
	
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserFollowingService getUserFollowingService() {
		return userFollowingService;
	}
	@Resource
	public void setUserFollowingService(UserFollowingService userFollowingService) {
		this.userFollowingService = userFollowingService;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody User user)
	{
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
	
	@RequestMapping(value="/follow")
	public void followUser(int userid,int followingid)
	{
		User user=userService.loadbyid(userid);
		User followingUser=userService.loadbyid(followingid);
		User_Following user_Following=new User_Following(user,followingUser);
		userFollowingService.follow(user_Following);
	}
	
	@RequestMapping(value="/unfollow")
	public void unfollowUser(int userid,int followingid)
	{
		User user=userService.loadbyid(userid);
		User followingUser=userService.loadbyid(followingid);
		User_Following user_Following=new User_Following(user,followingUser);
		userFollowingService.unfollow(user_Following);
	}
	
}
