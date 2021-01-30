package com.internal.ServicioWebREST.Exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String message;
	
	private final HttpStatus status;
	private final ZonedDateTime time;
	
	public ApiException(String message, HttpStatus status, ZonedDateTime time) {
		super();
		this.message = message;
		
		this.status = status;
		this.time = time;
	}

	public String getMessage() {
		return message;
	}



	public HttpStatus getStatus() {
		return status;
	}

	public ZonedDateTime getTime() {
		return time;
	}
	
	
	
}
