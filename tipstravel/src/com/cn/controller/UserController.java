package com.cn.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.entity.*;
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
	public void setUserFollowingService(
			UserFollowingService userFollowingService) {
		this.userFollowingService = userFollowingService;
	}

	@RequestMapping(value = "/test")
	public void detele() {
		userService.deleteUser(25);
		System.out.println("delete user");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	User addUser(@RequestBody User user) {
		User user_load_by_email = userService.loadbyemail(user.getEmail());
		if (user_load_by_email != null) {
			user_load_by_email.setMessage("邮箱已注册");
			return user_load_by_email;
		}
		User user_load_by_username = userService.loadbyusername(user
				.getUsername());
		if (user_load_by_username != null) {
			user_load_by_username.setMessage("用户名已注册");
			return user_load_by_username;
		}
		user.setAvatar("/image/avatar/user11.jpg");
		user.setIdentity("0");
		userService.addUser(user);
		User added_user=userService.loadbyemail(user.getEmail());
		User_Following user_Following=new User_Following(added_user,added_user);
		userFollowingService.follow(user_Following);
		user.setMessage("注册成功");
		return user;
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String followUser(@RequestBody Fetchmessage_info fetchmessage_info) {
		String message = "用户不存在";
		int userid = fetchmessage_info.getUserid();
		int followingid = fetchmessage_info.getFollowingid();
		User user = userService.loadbyid(userid);
		User followingUser = userService.loadbyid(followingid);
		if (user == null || followingUser == null)
			return message;
		User_Following user_Following = new User_Following(user, followingUser);
		User_Following followexist = userFollowingService.followexist(userid,
				followingid);
		if (followexist == null) {
			userFollowingService.follow(user_Following);
			message = "关注成功";
			return message;
		} else {
			userFollowingService.unfollow(followexist);
			message = "取消关注";
			return message;
		}
	}

	@RequestMapping(value = "recommendation", method = RequestMethod.POST)
	public @ResponseBody
	PaginationSupport<User> recommendation(
			@RequestBody Fetchmessage_info fetchmessage_info) {
		System.out.println("enter recommendation");
		PaginationSupport<User> ps = userService.recommendation();
		if (!ps.getData().iterator().hasNext())
			ps.setMessage("返回失败");
		else
			ps.setMessage("返回成功");
		System.out.println(ps);
		return ps;
	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String updatePassword(@RequestBody User user) {
		String message = "fail";
		User user_update = userService.loadbyid(user.getUser_id());
		System.out.println(user.getPassword());
		if (user_update != null) {
			if (!user.getPassword().equals(user_update.getPassword())) {
				user_update.setPassword(user.getPassword());
				userService.updateUser(user_update);
				return message = "success";
			} else {
				return message;
			}
		} else {
			return message;
		}
	}

	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String updateProfile(@RequestBody User user) {
		String message = "fail";
		User user_update = userService.loadbyid(user.getUser_id());
		if (user_update != null) {
			user_update.setCity(user.getCity());
			user_update.setUsername(user.getUsername());
			user_update.setGender(user.getGender());
			user_update.setIntroduction(user.getIntroduction());
			userService.updateUser(user_update);
			return message = "success";
		} else {
			return message;
		}
	}

	@RequestMapping(value = "/updateavatar", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String updateAvatar(@RequestParam("file") MultipartFile file,
			@RequestParam("userid") String userid) throws IllegalStateException, IOException {
		String message = "fail";
		int id = Integer.parseInt(userid);
		User user_update = userService.loadbyid(id);
		if (user_update != null) {
			String image_path = "F:\\git_repository\\tipstravel2\\tipstravel\\WebContent\\image\\avatar\\"
					+ file.getOriginalFilename();
			file.transferTo(new File(image_path));
			user_update.setAvatar("/image/message_pic/" + file.getOriginalFilename());

			userService.updateUser(user_update);
			return message = "success";
		} else {
			return message;
		}
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value = "/nachrecommendation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String nachrecommendation(@RequestBody Fetchmessage_info fetchmessage_info) {
		String info = "fail";
		int login_userid = fetchmessage_info.getUserid();
		User login_user = userService.loadbyid(login_userid);
		List<User> allfollowuser = fetchmessage_info.getAllFollowUsers();
		int size = allfollowuser.size();
		Iterator iterator = allfollowuser.iterator();
		for (int i = 0; i < size; i++) {
			User user = (User) iterator.next();
			User follow_user = userService.loadbyid(user.getUser_id());
			User_Following user_Following = new User_Following(login_user,
					follow_user);
			userFollowingService.follow(user_Following);
		}
		return info = "success";
	}
}
