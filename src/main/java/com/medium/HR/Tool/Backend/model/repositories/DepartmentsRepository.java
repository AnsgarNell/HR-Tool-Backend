package com.medium.HR.Tool.Backend.model.repositories;

import com.medium.HR.Tool.Backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The DepartmentsRepository class contains the implementation of
 * the JPA Repository for the departments
 */
public interface DepartmentsRepository extends JpaRepository<Department, String> {
}
