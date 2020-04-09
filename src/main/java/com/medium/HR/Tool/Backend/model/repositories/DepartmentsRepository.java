package com.medium.HR.Tool.Backend.model.repositories;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.projections.BasicDepartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The DepartmentsRepository class contains the implementation of
 * the JPA Repository for the departments
 */
public interface DepartmentsRepository extends JpaRepository<Department, String> {
    List<BasicDepartmentInfo> findAllByOrderByDeptNoAsc();
    Optional<BasicDepartmentInfo> findByDeptNo(String deptNo);
}
