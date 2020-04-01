package com.medium.HR.Tool.Backend.model.repositories;

import com.medium.HR.Tool.Backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
