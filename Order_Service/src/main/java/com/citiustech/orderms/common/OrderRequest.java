package com.citiustech.orderms.common;


public class OrderRequest {
	
	private Long customerId;
	private Long productId;
	private Integer quantity;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderRequest(Long customerId, Long productId, Integer quantity) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	

}
