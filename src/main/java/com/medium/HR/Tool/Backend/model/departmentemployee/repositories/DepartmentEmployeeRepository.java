package com.medium.HR.Tool.Backend.model.departmentemployee.repositories;

import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.departmentemployee.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.departmentemployee.DepartmentEmployeeId;
import com.medium.HR.Tool.Backend.model.departmentemployee.projections.DepartmentEmployeeBasicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, DepartmentEmployeeId> {
    Optional<Page<DepartmentEmployeeBasicInfo>> findAllByDepartment(Department department, Pageable pageable);
}
