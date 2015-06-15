package com.cn.entity;

import java.util.List;


public class Fetchmessage_info {
 int userid;
 int visitid;
 int startindex;
 String tagname;
 int messageid;
 int topicid;
 int followingid;
 private String context;
 private List<Tag> tag;
 private List<User> allFollowUsers;
 
public int getVisitid() {
	return visitid;
}
public void setVisitid(int visitid) {
	this.visitid = visitid;
}
public List<User> getAllFollowUsers() {
	return allFollowUsers;
}
public void setAllFollowUsers(List<User> allFollowUsers) {
	this.allFollowUsers = allFollowUsers;
}
public List<Tag> getTag() {
	return tag;
}
public void setTag(List<Tag> tag) {
	this.tag = tag;
}
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}

public int getFollowingid() {
	return followingid;
}
public void setFollowingid(int followingid) {
	this.followingid = followingid;
}
public int getTopicid() {
	return topicid;
}
public void setTopicid(int topicid) {
	this.topicid = topicid;
}
public int getMessageid() {
	return messageid;
}
public void setMessageid(int messageid) {
	this.messageid = messageid;
}
public String getTagname() {
	return tagname;
}
public void setTagname(String tagname) {
	this.tagname = tagname;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getStartindex() {
	return startindex;
}
public void setStartindex(int startindex) {
	this.startindex = startindex;
}
 
}
