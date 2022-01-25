package com.citiustech.orderms.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citiustech.orderms.common.OrderRequest;
import com.citiustech.orderms.common.Product;
import com.citiustech.orderms.exception.OrderNotFoundException;
import com.citiustech.orderms.model.Order;
import com.citiustech.orderms.model.OrderStatus;
import com.citiustech.orderms.repository.OrderRepository;
import com.citiustech.orderms.service.OrderService;

@RestController
public class OrderController {
	
	/*
	 * @Autowired private OrderService orderService;
	 */
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/order-create")
	public ResponseEntity<Object> addOrder(@RequestBody OrderRequest orderRequest) {
		if(orderRequest.getCustomerId()!=null&&orderRequest.getProductId()!=null
				&&orderRequest.getQuantity()!=null&&orderRequest.getQuantity()>=1) {
		Long customerId = orderRequest.getCustomerId();
		try {
			Object mycustomer=restTemplate.getForObject("http://customer-service/Customer-by-id?id="+customerId,Object.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		Integer price=0;
		Long productId = orderRequest.getProductId();
		try {
		price = (Integer) restTemplate.getForObject("http://product-service/product-price?productId="+productId, Object.class);}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		Integer quantity = orderRequest.getQuantity();
		Order order=new Order();
		order.setQuantity(quantity);
		order.setProductId(productId);
		order.setStatus(OrderStatus.placed);
		order.setAmount(price*quantity);
		order.setCustomerId(orderRequest.getCustomerId());
		repo.save(order);
		return ResponseEntity.ok(order);
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("customerId/productId/quantity cannot be null");
		}
	
	@GetMapping("/order-by-cid")
	public ResponseEntity<Object> getOrderByCustomerId(@RequestParam Long customerId) {
		try {
			Object mycustomer=restTemplate.getForObject("http://customer-service/Customer-by-id?id="+customerId,Object.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		List<Order> orders = repo.findByCustomerId(customerId);
		if(orders.isEmpty()) return ResponseEntity.ok("No order found for given customer!");
		else return ResponseEntity.ok().body(orders);
		
	}
	
	@GetMapping("/orders")
	public ResponseEntity<Object> getAllOrders(){
		List<Order> orders = repo.findAll();
		if(orders.isEmpty()) return ResponseEntity.ok("No order found");
		return ResponseEntity.ok().body(orders);
		
	}
	
	@PatchMapping("/update-order-status")
	public ResponseEntity<String> updateOrder(@RequestParam Long orderId,@RequestParam OrderStatus status){
		
		Optional<Order> myOrder = repo.findById(orderId);
		if(myOrder.isPresent()) {
			myOrder.get().setStatus(status);
			repo.save(myOrder.get());
			return ResponseEntity.ok("Order status updated!");
		}
		throw new OrderNotFoundException(orderId);
		//return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No order found!");
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) {
		Optional<Order> order = repo.findById(orderId);
		if(order.isPresent()) return ResponseEntity.ok(order.get());
		throw new OrderNotFoundException(orderId);
		
	}

}
