package com.blogspot.javasolutionsguide.springqualifierdemo;

import com.blogspot.javasolutionsguide.springqualifierdemo.property.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringQualifierDemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringQualifierDemoApplication.class, args);
		Employee employee = applicationContext.getBean(Employee.class);
		employee.displayAddress();
		
	}

}
