package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CarteCreditExceptionAdvice {
	
	@ExceptionHandler(CarteCreditException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String carteCreditExceptionHandler(CarteCreditException e) {
		return String.format("{\"%s\": \"%s\"}", "error", e.getMessage());
	}
}
