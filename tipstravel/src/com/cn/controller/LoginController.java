package com.cn.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cn.entity.User;
import com.cn.service.UserService;

@Controller
@SessionAttributes("loginUser")
public class LoginController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
//	@RequestMapping(value="/login",method=RequestMethod.GET)
//	public String login() {
//		return "login";
//	}

	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody User login(@RequestBody User user)
	{

		System.out.println("aaaaaaaaaaaaaaaaaa");
//		return null;
		User u = userService.login(user.getEmail());
		System.out.println(u);
		System.out.println("login check");
//		System.out.println((u.getPassword()).equals(password));
		if(u==null) 
		{
			user.setMessage("用户名不存在");
			System.out.println(user);
			return user;
			
//			System.out.println(user);
//			return "/login";
		}
		
		else if(!(u.getPassword()).equals(user.getPassword()))
		{
			System.out.println(u.getPassword());
			System.out.println(user.getPassword());
			user.setMessage("密码不正确");
			return user;
			//return "/login";
		}
		else
		{
			u.setMessage("登陆成功");		
		}
		return u;
		
	}
	
	@RequestMapping(value="/logout")
	public String logout(Model model,HttpSession session)
	{
		model.asMap().remove("loginUser");
		return "redirect:/login";
	}
}
