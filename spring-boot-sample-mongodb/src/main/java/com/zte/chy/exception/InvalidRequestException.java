package com.zte.chy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, code = HttpStatus.NOT_ACCEPTABLE, reason = "EXISTING A BAD REQUEST !!!")
public class InvalidRequestException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidRequestException(String message){
		super(message);
	}
}
