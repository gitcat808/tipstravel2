package com.cn.controller;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.AnnotationConfiguration; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.entity.User;

@Controller
@SuppressWarnings("deprecation")
public class UserController {
	SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	Transaction ts;
	
	public  UserController(){}
	
	@RequestMapping(value = "/user/add")
	public String addUser() {
		System.out.println("begin");
		try {
		
			 System.out.println("创建sessionFactory成功");  
		} catch (Exception e) {
			e.printStackTrace();  
            System.out.println("创建sessionFactory出错");  
		}
		User user=new User();
		user.setUsername("teset");
		user.setPassword("123");
		user.setEmail("1@fa");
		Session session = sessionFactory.getCurrentSession();
		ts=session.beginTransaction();
		session.save(user);
		ts.commit();
		System.out.println("test usercontroller");
		return "/user/test";
	}
}
