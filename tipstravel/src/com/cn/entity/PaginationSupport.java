package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

public class PaginationSupport {
	private List<Message> data=new ArrayList<Message>();
	private String message;
	
	
	@Override
	public String toString() {
		return "PaginationSupport [data=" + data + ", message=" + message + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<Message> getData() {
		return data;
	}
	public void setData(List<Message> data) {
		this.data = data;
	}

}
