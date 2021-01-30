package com.internal.ServicioWebREST.Exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestCustomExceptionHandler {
	
	 @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException ex, HttpServletRequest request) {
			ApiException apiexception = new ApiException(ex.getMessage(),HttpStatus.BAD_REQUEST,ZonedDateTime.now(ZoneId.of("Z")));
			return new ResponseEntity<>(apiexception,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
}
