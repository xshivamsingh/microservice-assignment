package com.citiustech.orderms.exception;

public class OrderNotFoundException extends RuntimeException{

	public OrderNotFoundException(Long id) {
		super(String.format("Order with id "+id+" not found!"));
		// TODO Auto-generated constructor stub
	}
	
	
}
