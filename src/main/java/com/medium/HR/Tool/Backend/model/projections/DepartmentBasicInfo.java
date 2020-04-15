package com.medium.HR.Tool.Backend.model.projections;

import com.medium.HR.Tool.Backend.model.Department;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "basicDepartmentInfo",
        types = { Department.class })
public interface DepartmentBasicInfo {
    String getDeptNo();
    String getDeptName();
}
