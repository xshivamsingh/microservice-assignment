package com.impact.productms.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductNotFoundException.class)
	private ResponseEntity<Object> handlCustomerNotFoundException(ProductNotFoundException e) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Exception", "ProductNotFoundException" );
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message", e.getMessage());
		System.out.println("ProductNotFoundException: \n"+ body);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

}
