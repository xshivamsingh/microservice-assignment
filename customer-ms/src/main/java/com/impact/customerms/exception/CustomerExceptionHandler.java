package com.impact.customerms.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomerNotFoundException.class)
	private ResponseEntity<Object> handlCustomerNotFoundException(CustomerNotFoundException e) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Exception", "CustomerNotFoundException" );
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message", e.getMessage());
		System.out.println("CustomerNotFoundException: \n"+ body);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

}
