package com.blogspot.javasolutionsguide.springqualifierdemo.constructor.parameter;

import org.springframework.stereotype.Component;

@Component("currentAddress")
public class CurrentAddress implements Address {

	@Override
	public String getAddress() {
		return "123,XYZ, Current State";
	}

}
