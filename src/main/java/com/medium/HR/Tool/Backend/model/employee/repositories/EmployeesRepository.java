package com.medium.HR.Tool.Backend.model.employee.repositories;

import com.medium.HR.Tool.Backend.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The EmployeesRepository class contains the implementation of
 * the JPA Repository for the employees
 */
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
}
