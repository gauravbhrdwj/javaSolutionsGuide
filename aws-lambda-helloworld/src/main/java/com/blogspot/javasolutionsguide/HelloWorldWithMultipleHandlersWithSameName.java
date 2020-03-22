package com.blogspot.javasolutionsguide;

import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorldWithMultipleHandlersWithSameName {
	
	public String handler(String name) {
		return String.format("Hello %s.", name);
	}
	
	public String handler(String name, Context context) {
		
		return String.format("Hello %s%s.", name,   " Memory Allocated:" + context.getMemoryLimitInMB());
	}

}
