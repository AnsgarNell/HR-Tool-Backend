package com.medium.HR.Tool.Backend.model.department.projections;

import com.medium.HR.Tool.Backend.model.department.Department;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "basicDepartmentInfo",
        types = { Department.class })
public interface DepartmentBasicInfo {
    String getDeptNo();
    String getDeptName();
}
