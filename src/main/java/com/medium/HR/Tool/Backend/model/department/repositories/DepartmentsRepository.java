package com.medium.HR.Tool.Backend.model.department.repositories;

import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.department.projections.DepartmentBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The DepartmentsRepository class contains the implementation of
 * the JPA Repository for the departments
 */
public interface DepartmentsRepository extends JpaRepository<Department, String> {
    List<DepartmentBasicInfo> findAllByOrderByDeptNoAsc();
}
