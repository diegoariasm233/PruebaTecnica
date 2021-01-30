package com.internal.ServicioWebREST.Models;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.internal.ServicioWebREST.Until.MyDoubleDesirializer;

public class PayResponse {
	@JsonSerialize(using=MyDoubleDesirializer.class)
	private double salarytopay;
	@JsonIgnore
	private HttpStatus status;
	private String message;
	public PayResponse() { super(); }
	
	public PayResponse(double salarytopay, HttpStatus status) {
		super();
		this.salarytopay = salarytopay;
		this.status = status;
	}
	
	public double getSalarytopay() {
		return salarytopay;
	}
	
	public void setSalarytopay(double salarytopay) {
		this.salarytopay = salarytopay;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
