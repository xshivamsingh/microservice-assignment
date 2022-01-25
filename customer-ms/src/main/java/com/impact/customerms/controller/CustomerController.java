package com.impact.customerms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.impact.customerms.common.Order;
import com.impact.customerms.exception.CustomerNotFoundException;
import com.impact.customerms.model.Address;
import com.impact.customerms.model.Customer;
import com.impact.customerms.repository.CustomerRepo;

@RestController
@RefreshScope
public class CustomerController {
	
	@Autowired
	private CustomerRepo repository ;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Customer isPresent(Long cid){
		
		Optional<Customer> myCustomer = repository.findById(cid);
		
		if(myCustomer.isPresent()) return myCustomer.get();
		throw new CustomerNotFoundException(cid);
		/*
		 * boolean cstatus= myCustomer.isPresent()?true:false; return
		 * ResponseEntity.ok(cstatus);
		 */
	}
	
	@PostMapping("/add-customer")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		 if(customer.getEmail()==null){
			  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email is must!");
		  }
		customer.setStatus("ACTIVE");
		Customer myCustomer=repository.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(myCustomer);
	}
	
	@PatchMapping("/update-customer")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
		  
		  Customer mycustomer = isPresent(customer.getCustomerId());
		  if(customer.getEmail()==null||customer.getStatus()==null||customer.getStatus().equals("INACTIVE")){
			  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Customer cannot be updated");
		  }
		  repository.save(customer);
		  return ResponseEntity.status(HttpStatus.OK).body("Customer updated");
		 
		/*
		 * Optional<Customer> myCustomer =repository.findById(Customer.getCustomerId());
		 * if(myCustomer.isPresent()) { repository.save(Customer); return
		 * ResponseEntity.status(HttpStatus.OK).body("Customer updated"); } throw new
		 * CustomerNotFoundException(Customer.getCustomerId());
		 */
		 
	}
		
	@GetMapping("/Customer-by-id")
	public ResponseEntity<Customer> getCustomerById(@RequestParam Long id) {
		Optional<Customer> customer = repository.findById(id);
		if(customer.isPresent()&&customer.get().getStatus().equals("ACTIVE")) return ResponseEntity.status(HttpStatus.FOUND).body(customer.get());
		throw new CustomerNotFoundException(id);
	}
	
	@GetMapping("/Customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@DeleteMapping("/delete-Customer")
	public ResponseEntity<String> deleteCustomer(@RequestParam Long id) {
		  Optional<Customer> myCustomer = repository.findById(id);
		  if(myCustomer.isPresent()&&myCustomer.get().getStatus().equals("ACTIVE")) { 
			  myCustomer.get().setStatus("INACTIVE");
			  repository.save(myCustomer.get());
			  return ResponseEntity.ok("Customer deleted!");}
		  throw new CustomerNotFoundException(id);
	}
	
	@PostMapping("/customer-add-address")
	public ResponseEntity<String> addAddress(@RequestParam Long id, @RequestBody Address address){
		Optional<Customer> myCustomer = repository.findById(id);
		if(myCustomer.isPresent()) {
			myCustomer.get().addAddress(address);
			repository.save(myCustomer.get());
			return ResponseEntity.ok("Address added!");
		}
		
		throw new CustomerNotFoundException(id);
	}
	
	/*
	 * @PostMapping("/customer-add-order") public ResponseEntity<String>
	 * addOrder(@RequestParam Long custmerId, @RequestParam Long orderId){
	 * Optional<Customer> myCustomer = repository.findById(custmerId);
	 * if(myCustomer.isPresent()) { myCustomer.get().addOrders(orderId);
	 * repository.save(myCustomer.get()); return ResponseEntity.ok("Order added!");
	 * } throw new CustomerNotFoundException(custmerId); }
	 */
	@GetMapping("/customer-orders")
	public ResponseEntity<Object> getOrders(@RequestParam Long customerId){
		Optional<Customer> myCustomer = repository.findById(customerId);
		if(myCustomer.isPresent()) {
			
			Object orders = restTemplate.getForObject("http://order-service/order-by-cid?customerId="+customerId, Object.class);
			return ResponseEntity.ok(orders);
			
			/* Logic 2----------------
			 * List<Object> orders = new ArrayList<>(); List<Long> myOrderIds =
			 * myCustomer.get().getOrders(); for(long x : myOrderIds) { Object order =
			 * restTemplate.getForObject("http://ORDERMS/orderms/orders/"+x, Object.class);
			 * orders.add(order); } return ResponseEntity.ok(orders);
			 */
		}
		throw new CustomerNotFoundException(customerId);
	}
	
}
