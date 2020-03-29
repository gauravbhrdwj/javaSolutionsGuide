package com.blogspot.javasolutionsguide.springqualifierdemo.property;

import org.springframework.stereotype.Component;

@Component("permanentAddress")
public class PermanentAddress implements Address {
	
	@Override
	public String getAddress() {
		return "345,ABC, Permanent State";
	}

}
