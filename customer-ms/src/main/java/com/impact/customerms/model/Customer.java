package com.impact.customerms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Table(name = "CUSTOMER_TB")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_ID")
	private Long customerId;

	private String name;
	
	@Column(nullable = false)
	private String email;
	private String contact;
	
	@Column(nullable = false)
	private String status;

	@ElementCollection
    @CollectionTable(name = "CUSTOMER_ADDRESS",
    				joinColumns = {
    						@JoinColumn(name="CustomerId",referencedColumnName = "C_ID")
    				})
	private List<Address> addresses = new ArrayList<Address>();

	/*
	 * @ElementCollection
	 * 
	 * @JoinTable(name = "CUSTOMER_ORDERS") private List<Long> orderIDs = new
	 * ArrayList<Long>();
	 */

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/*
	 * public List<Long> getOrders() { return orderIDs; }
	 * 
	 * public void setOrders(List<Long> orders) { this.orderIDs = orders; }
	 * 
	 * public void addOrders(Long orderID) { this.orderIDs.add(orderID); }
	 */

	public void addAddress(Address address) {
		this.addresses.add(address);
	}
}
