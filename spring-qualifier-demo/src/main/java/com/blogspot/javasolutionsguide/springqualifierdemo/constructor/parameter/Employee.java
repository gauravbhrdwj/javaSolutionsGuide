package com.blogspot.javasolutionsguide.springqualifierdemo.constructor.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Employee {

    private final Address address;

    public Employee(@Qualifier("currentAddress") Address address) {
        this.address = address;
    }

    public void displayAddress() {
        System.out.println("Employee address is: " + address.getAddress());
    }

}
