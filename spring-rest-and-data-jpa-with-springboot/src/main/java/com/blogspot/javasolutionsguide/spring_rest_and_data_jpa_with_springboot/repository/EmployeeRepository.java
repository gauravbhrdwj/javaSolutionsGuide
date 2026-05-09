package com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.repository;

import com.blogspot.javasolutionsguide.spring_rest_and_data_jpa_with_springboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
