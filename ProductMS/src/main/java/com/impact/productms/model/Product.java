package com.impact.productms.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "PRODUCT_TB")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_ID")
	private Long productId;

	@Column(name = "P_NAME",nullable = false)
	private String name;
	private String category;
	@Column(nullable = false)
	private Integer price;

	@ElementCollection
	@CollectionTable(name = "product_details_mapping", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "P_ID")
			})
	@MapKeyColumn(name = "detail_name")
	@Column(name = "detail_value")
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
	
	public void removeDetails(String s) {
		this.details.remove(s);
	}
	

}
