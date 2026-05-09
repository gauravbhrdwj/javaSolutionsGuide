package com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.service;

import com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> retrieveEmployees();

    Optional<Employee> getEmployee(Long employeeId);

    void saveEmployee(Employee employee);

    void deleteEmployee(Long employeeId);

    Employee updateEmployee(Employee employee);
}
