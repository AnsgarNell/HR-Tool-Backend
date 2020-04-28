package com.medium.HR.Tool.Backend.model.departmentemployee.projections;

import com.medium.HR.Tool.Backend.model.departmentemployee.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.employee.projections.EmployeeBasicInfo;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "departmentEmployeeBasicInfo",
        types = { DepartmentEmployee.class })
public interface DepartmentEmployeeBasicInfo {
    EmployeeBasicInfo getEmployee();
}
