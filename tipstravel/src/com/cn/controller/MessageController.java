package com.cn.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.cn.entity.Fetchmessage_info;
import com.cn.entity.Like;
import com.cn.entity.Message;
import com.cn.entity.PaginationSupport;
import com.cn.entity.Tag;
import com.cn.entity.Tag_Message;
import com.cn.entity.User;
import com.cn.service.LikeService;
import com.cn.service.MessageService;
import com.cn.service.TagMessageService;
import com.cn.service.TagService;
import com.cn.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private MessageService messageService;
	private UserService userService;
	private LikeService likeService;
	private TagService tagService;
	private TagMessageService tagMessageService;

	public TagMessageService getTagMessageService() {
		return tagMessageService;
	}

	@Resource
	public void setTagMessageService(TagMessageService tagMessageService) {
		this.tagMessageService = tagMessageService;
	}

	public TagService getTagService() {
		return tagService;
	}

	@Resource
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public LikeService getLikeService() {
		return likeService;
	}

	@Resource
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	@Resource
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	// 还没写完，这里收json的message需要重新写一个实体
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String addMessage(@RequestBody Fetchmessage_info fetchmessage_info) {
		System.out.println("enter message add");
		String info = "上传失败";
		Message message = new Message();
		User user = userService.loadbyid(fetchmessage_info.getUserid());
		if (user == null)
			return info;
		message.setContext(fetchmessage_info.getContext());
		message.setUser(user);
		message.setLike_count(0);
		// date
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dFormat.format(new Date());
		message.setMessage_date(date);
		// image

		messageService.addMessage(message);
		// tag
		Message addedMessage = messageService.loadbydate(date);
		System.out.println(addedMessage);
		if (addedMessage == null)
			return info;
		List<Tag> tagList = fetchmessage_info.getTag();
		int size = tagList.size();
		Iterator iterator = tagList.iterator();
		for (int i = 0; i < size; i++) {
			Tag loaded_tag = (Tag) iterator.next();
			String loaded_tag_name = loaded_tag.getTag_name();
			Tag tag = tagService.loadbyname(loaded_tag_name);
			if (tag == null) {
				Tag added_tag = new Tag();
				added_tag.setTag_name(loaded_tag_name);
				tagService.addTag(added_tag);
				Tag_Message tag_Message = new Tag_Message(added_tag,
						addedMessage);
				tagMessageService.addTagMessage(tag_Message);
			} else {
				Tag_Message tag_Message = new Tag_Message(tag, addedMessage);
				tagMessageService.addTagMessage(tag_Message);
			}
		}

		return info = "上传成功";
	}

	@RequestMapping(value = "/homepage", method = RequestMethod.POST)
	public @ResponseBody
	PaginationSupport<Message> showhomepage(
			@RequestBody Fetchmessage_info fetchmessage_info) {
		PaginationSupport<Message> ps = messageService.showhome(
				fetchmessage_info.getUserid(),
				fetchmessage_info.getStartindex());
		if (!ps.getData().iterator().hasNext())
			ps.setMessage("返回失败");
		else
			ps.setMessage("返回成功");
		return ps;
	}

	@RequestMapping(value = "/following", method = RequestMethod.POST)
	public @ResponseBody
	PaginationSupport<Message> showfollowing(
			@RequestBody Fetchmessage_info fetchmessage_info) {
		PaginationSupport<Message> ps = messageService.showfollowing(
				fetchmessage_info.getUserid(),
				fetchmessage_info.getStartindex());
		if (!ps.getData().iterator().hasNext())
			ps.setMessage("返回失败");
		else
			ps.setMessage("返回成功");
		return ps;
	}

	@RequestMapping(value = "/like", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String likemessage(@RequestBody Fetchmessage_info fetchmessage_info) {
		String message = "notexist";
		int userid = fetchmessage_info.getUserid();
		int messageid = fetchmessage_info.getMessageid();
		User userEntity = userService.loadbyid(userid);
		Message messageEntity = messageService.loadbyid(messageid);
		int like_count = messageEntity.getLike_count();
		if (userEntity == null || messageEntity == null)
			return message;
		Like like_exist = likeService.likeexist(userid, messageid);
		if (like_exist != null) {
			int likeid = like_exist.getLike_id();
			messageEntity.setLike_count(--like_count);
			likeService.delete(likeid);
			messageService.updateMessage(messageEntity);
			return message = "dislikesuccess";
		} else {
			Like like = new Like();
			like.setUser(userEntity);
			like.setMessage(messageEntity);
			likeService.add(like);
			messageEntity.setLike_count(++like_count);
			messageService.updateMessage(messageEntity);
			return message = "likesuccess";
		}
	}

	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String upload(@RequestParam("file") MultipartFile file,
			@RequestParam("userid") String userid,
			@RequestParam("content") String content,
			@RequestParam("tags") List<String> tags
			) throws IOException {
		System.out.println(tags.size());
		String info = "fail";
		try {

			Message message = new Message();
			int id = Integer.parseInt(userid);
			User user = userService.loadbyid(id);
			if (user == null)
				return info;
			message.setContext(content);
			message.setUser(user);
			message.setLike_count(0);
			// date
			SimpleDateFormat dFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String date = dFormat.format(new Date());
			message.setMessage_date(date);
			String image_path = "F:\\git_repository\\tipstravel2\\tipstravel\\WebContent\\image\\message_pic\\"
					+ file.getOriginalFilename();
			file.transferTo(new File(image_path));
			message.setImage("/image/message_pic/" + file.getOriginalFilename());
			messageService.addMessage(message);
			// tag
			Message addedMessage = messageService.loadbydate(date);
			System.out.println(addedMessage);
			if (addedMessage == null)
				return info;
			List<String> tagList = tags;
			int size = tagList.size();
			Iterator iterator = tagList.iterator();
			for (int i = 0; i < size; i++) {
				String loaded_tag_name=(String) iterator.next();
				Tag tag = tagService.loadbyname(loaded_tag_name);
				if (tag == null) {
					Tag added_tag = new Tag();
					added_tag.setTag_name(loaded_tag_name);
					tagService.addTag(added_tag);
					Tag_Message tag_Message = new Tag_Message(added_tag,
							addedMessage);
					tagMessageService.addTagMessage(tag_Message);
				} else {
					Tag_Message tag_Message = new Tag_Message(tag, addedMessage);
					tagMessageService.addTagMessage(tag_Message);
				}
			}

			return info = "success";
		} catch (Exception e) {
			return info;
		}
	}
}
