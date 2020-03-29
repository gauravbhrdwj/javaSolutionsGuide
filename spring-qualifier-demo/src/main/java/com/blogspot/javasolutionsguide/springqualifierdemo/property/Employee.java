package com.blogspot.javasolutionsguide.springqualifierdemo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	@Qualifier("permanentAddress")
	@Autowired
	private Address address;
	
	
	public void displayAddress() {
		System.out.println("Employee address is: " + address.getAddress());
	}

}
