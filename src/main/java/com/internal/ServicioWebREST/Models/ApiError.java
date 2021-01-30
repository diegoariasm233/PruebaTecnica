package com.internal.ServicioWebREST.Models;

import java.util.Date;

public class ApiError {
	private Date timestap;
	private String message;
	
	public ApiError() {}
	
	public ApiError(Date timestap, String message) {
		super();
		this.timestap = timestap;
		this.message = message;
	}
	public Date getTimestap() {
		return timestap;
	}
	public void setTimestap(Date timestap) {
		this.timestap = timestap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
