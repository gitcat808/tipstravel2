package com.cn.entity;

public class Fetchmessage_info {
 int userid;
 int startindex;
 String tagname;
 int messageid;
 int topicid;
 int followingid;
 
 
 
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
