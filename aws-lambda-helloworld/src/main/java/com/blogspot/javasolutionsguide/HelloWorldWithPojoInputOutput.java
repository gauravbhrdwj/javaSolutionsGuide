package com.blogspot.javasolutionsguide;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blogspot.javasolutionsguide.input.Input;
import com.blogspot.javasolutionsguide.output.Output;

public class HelloWorldWithPojoInputOutput implements RequestHandler<Input,Output>{

	@Override
	public Output handleRequest(Input input, Context context) {
		String message = String.format("Hello %s%s.", input.getName() , " " + "from" + context.getFunctionName());
		return new Output(message);
	}

	
}
