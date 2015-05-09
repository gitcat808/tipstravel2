package com.cn.controller;

import javax.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.entity.PaginationSupport;
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
	
	@RequestMapping(value="/test")
	public void detele()
	{
		userService.deleteUser(25);
		System.out.println("delete user");
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody User user)
	{
		User user_load_by_email=userService.loadbyemail(user.getEmail());
		if(user_load_by_email!=null)
		{
			user_load_by_email.setMessage("邮箱已注册");
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
	
	@RequestMapping(value="recommendation")
	public @ResponseBody PaginationSupport<User> recommendation()
	{
		PaginationSupport<User> ps=userService.recommendation();
		if(!ps.getData().iterator().hasNext())ps.setMessage("返回失败");
		else ps.setMessage("返回成功");
		System.out.println(ps);
		return ps;
	}
	
	@RequestMapping(value="/update")
	public String updateUser(User user)
	{
		String message="更新失败";
		User user_update=userService.loadbyid(user.getUser_id());
		if(user_update!=null)
		{
			user_update.setAvatar(user.getAvatar());
			user_update.setIntroduction(user.getIntroduction());
			userService.updateUser(user_update);
			return message="更新成功";
		}
		else {
			return message;
		}
		
	}
}
