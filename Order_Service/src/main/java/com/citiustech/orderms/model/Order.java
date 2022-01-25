package com.citiustech.orderms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_tb")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Integer amount;
	
	@Column(nullable = false)
	private Long customerId;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", status=" + status + ", productId=" + productId + ", quantity="
				+ quantity + ", amount=" + amount + ", customerId=" + customerId + "]";
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long orderId, OrderStatus status, Long productId, Integer quantity, Integer amount, Long customerId) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	

}
