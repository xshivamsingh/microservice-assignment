package com.impact.customerms.exception;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(Long id) {
		super(String.format("Customer with id "+id+" not found!"));
		// TODO Auto-generated constructor stub
	}
	
	
}
