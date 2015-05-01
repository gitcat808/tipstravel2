package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

public class PaginationSupport {
	@JsonManagedReference
	private List<Message> data=new ArrayList<Message>();
	@JsonBackReference
	private String message;
	
	
	@Override
	public String toString() {
		return "PaginationSupport [data=" + data + ", message=" + message + "]";
	}
	
	public String getMessage() {
		return message;
	}
	//@JsonBackReference
	public void setMessage(String message) {
		this.message = message;
	}
//	@JsonManagedReference
	public List<Message> getData() {
		return data;
	}
	//@JsonManagedReference
	public void setData(List<Message> data) {
		this.data = data;
	}

}
