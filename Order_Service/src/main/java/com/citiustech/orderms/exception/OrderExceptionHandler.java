package com.citiustech.orderms.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(OrderNotFoundException.class)
	private ResponseEntity<Object> handlCustomerNotFoundException(OrderNotFoundException e) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Exception", "OrderNotFoundException" );
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message", e.getMessage());
		System.out.println("OrderNotFoundException: \n"+ body);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

}
