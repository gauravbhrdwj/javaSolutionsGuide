package com.blogspot.javasolutionsguide;

import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorld {
	
	public String sayHelloWorldWithoutContext(String name) {
		return String.format("Hello %s.", name);
	}
	
	//We need to add aws-lambda-java-core dependency if we add Context as parameter.
	public String sayHelloWorldWithContext(String name, Context context) {
		
		context.getLogger().log("Lambda Function Name:" + context.getFunctionName() +
				"Version:" + context.getFunctionVersion() + 
				"Arn:" + context.getInvokedFunctionArn() +
				"Allocated Memory:" + context.getMemoryLimitInMB() +
				"Remaining Time:"+ context.getRemainingTimeInMillis());
		return String.format("Hello %s.", name);
	}

}
