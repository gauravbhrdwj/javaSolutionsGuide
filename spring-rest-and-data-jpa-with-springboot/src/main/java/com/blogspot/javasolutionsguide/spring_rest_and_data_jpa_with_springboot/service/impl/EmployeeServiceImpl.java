package com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.service.impl;

import com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.entity.Employee;
import com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.repository.EmployeeRepository;
import com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
