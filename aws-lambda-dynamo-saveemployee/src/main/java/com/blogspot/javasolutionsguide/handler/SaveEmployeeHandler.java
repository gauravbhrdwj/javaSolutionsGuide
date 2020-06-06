package com.blogspot.javasolutionsguide.handler;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.blogspot.javasolutionsguide.request.Employee;
import com.blogspot.javasolutionsguide.response.Response;

public class SaveEmployeeHandler implements RequestHandler<Employee, Response>{
	
	private DynamoDB dynamoDb;
	private String DYNAMO_DB_TABLE_NAME = "Employee";
	private Regions REGION = Regions.US_EAST_1;

	public SaveEmployeeHandler() {
		this.initDynamoDbClient();
	}


	@Override
	public Response handleRequest(Employee personRequest, Context context) {
	    persistData(personRequest);
	    Response personResponse = new Response();
	    personResponse.setMessage("Message Saved Successfully");
		return personResponse;
	}
	
	private void initDynamoDbClient() {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
	
	private PutItemOutcome persistData(Employee employee) {
		Table table = dynamoDb.getTable(DYNAMO_DB_TABLE_NAME);
		PutItemOutcome outcome = table.putItem(new PutItemSpec().withItem(
				new Item().withNumber("empId", employee.getEmpId())
				           .withString("firstName", employee.getFirstName())
				           .withString("lastName", employee.getLastName())));
		return outcome;
	}

	

}
