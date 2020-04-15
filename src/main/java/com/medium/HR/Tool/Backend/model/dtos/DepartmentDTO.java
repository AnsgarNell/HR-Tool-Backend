package com.medium.HR.Tool.Backend.model.dtos;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {
    Department department;
    List<DepartmentEmployee> employees;
}
