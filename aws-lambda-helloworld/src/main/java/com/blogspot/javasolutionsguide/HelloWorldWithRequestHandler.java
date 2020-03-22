package com.blogspot.javasolutionsguide;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloWorldWithRequestHandler implements RequestHandler<Object,Object> {

	@Override
	public Object handleRequest(Object input, Context context) {
		return String.format("Hello %s%s.", input ," from " + context.getFunctionName());
	}

}
