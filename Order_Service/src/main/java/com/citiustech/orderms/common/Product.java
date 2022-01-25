package com.citiustech.orderms.common;

import java.util.*;

public class Product {

	private Long productId;

	private String name;
	private String category;
	private Integer price;

	private Map<String, String> details = new HashMap<String,String>();

	public Long getProductId() {
	return productId;
	}



	public void setProductId(Long productId) {
	this.productId = productId;
	}



	public String getName() {
	return name;
	}



	public void setName(String name) {
	this.name = name;
	}



	public String getCategory() {
	return category;
	}



	public void setCategory(String category) {
	this.category = category;
	}



	public Integer getPrice() {
	return price;
	}



	public void setPrice(Integer price) {
	this.price = price;
	}




	public Map<String, String> getDetails() {
	//return Collections.unmodifiableMap(details);
	return details;
	}

	public void setDetails(Map<String, String> details) {
	this.details = details;
	}

	public void addDetails(String s, String o) {
	this.details.put(s, o);
	}
}
