package com.cn.controller;

import javax.annotation.Resource;
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
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody User login(@RequestBody  User user)
	{
		System.out.println("enter login");
		System.out.println("user password:"+user.getPassword());
		User u = userService.login(user.getEmail());
		System.out.println("u password:"+u.getPassword());
		if(u==null) 
		{
			user.setMessage("用户名不存在");
			System.out.println(user);
			return user;
		}
				
		else if(!(u.getPassword()).equals(user.getPassword()))
		{
			user.setMessage("密码不正确");
			return user;
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
