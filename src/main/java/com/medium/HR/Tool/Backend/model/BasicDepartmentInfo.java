package com.medium.HR.Tool.Backend.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "basicDepartmentInfo",
        types = { Department.class })
public interface BasicDepartmentInfo {
    String getDeptNo();
    String getDeptName();
}
