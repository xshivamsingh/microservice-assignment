package com.impact.productms.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(Long id) {
		super(String.format("Product with id "+id+" not found!"));
		// TODO Auto-generated constructor stub
	}
	
	
}
