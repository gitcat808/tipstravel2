package com.cn.entity;

import org.springframework.web.multipart.MultipartFile;

public class RequestMessageInfo {

	MultipartFile file;
	String userid;
	String content;
	String tags;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "RequestMessageInfo [userid=" + userid + ", content=" + content
				+ ", tags=" + tags + "]";
	}
	
	
	
}
