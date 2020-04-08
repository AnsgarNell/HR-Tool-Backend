package com.medium.HR.Tool.Backend.model.repositories;

import com.medium.HR.Tool.Backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The EmployeesRepository class contains the implementation of
 * the JPA Repository for the employees
 */
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findAll(Pageable pageable);
}
