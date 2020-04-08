package com.medium.HR.Tool.Backend.model.projections;

import com.medium.HR.Tool.Backend.model.Department;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "departmentWithoutEmployees",
        types = { Department.class })
public interface DepartmentWithoutEmployees {
    String getDeptNo();
    String getDeptName();
    String getManagers();
}
