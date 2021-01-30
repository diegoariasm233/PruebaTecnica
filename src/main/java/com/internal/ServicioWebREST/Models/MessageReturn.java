package com.internal.ServicioWebREST.Models;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageReturn {
	
	private EmployeeModel employee;
	private String message;
	@JsonIgnore
	private HttpStatus status;
	public MessageReturn() {
		super();
	}
	
	public MessageReturn(EmployeeModel employee, String message,HttpStatus status ) {
		super();
		this.employee = employee;
		this.message = message;
		this.status = status;
	}
	public EmployeeModel getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
