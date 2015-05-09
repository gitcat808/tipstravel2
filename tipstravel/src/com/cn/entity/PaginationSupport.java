package com.cn.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

public class PaginationSupport<T> {
	@JsonManagedReference
	private List<T> data;
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
	public List<T> getData() {
		return data;
	}
	//@JsonManagedReference
	public void setData(List<T> data) {
		this.data = data;
	}

}
