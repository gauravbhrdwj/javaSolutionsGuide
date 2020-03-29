package com.blogspot.javasolutionsguide.springqualifierdemo.constructor.parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	private Address address;
	
	@Autowired
	public Employee(@Qualifier("currentAddress") Address address) {
		this.address = address;
	}
	
	
	public void displayAddress() {
		System.out.println("Employee address is: " + address.getAddress());
	}

}
