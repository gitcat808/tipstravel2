package com.cn.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	//SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	//Transaction ts;
	
	public  UserController()
	{
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		System.out.println("12345");
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value = "/adding",method=RequestMethod.POST)
	public String addUser(@Validated User user) {
//		System.out.println("begin");
//		try {
//		
//			 System.out.println("创建sessionFactory成功");  
//		} catch (Exception e) {
//			e.printStackTrace();  
//            System.out.println("创建sessionFactory出错");  
//		}
//		Session session = sessionFactory.getCurrentSession();
//		ts=session.beginTransaction();
//		session.save(user);
//		ts.commit();
//		System.out.println("test usercontroller");
		
		return "/user/test";
	}
}
