package com.medium.HR.Tool.Backend.model.projections;

import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "departmentEmployeeBasicInfo",
        types = { DepartmentEmployee.class })
public interface DepartmentEmployeeBasicInfo {
    EmployeeBasicInfo getEmployee();
}
