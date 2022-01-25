package com.impact.customerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impact.customerms.model.Customer;



@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>  {

	List<Customer> findByCustomerId(Long customerId);
}
