package com.medium.HR.Tool.Backend.model.departmentmanager;

import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.employee.Employee;

import java.io.Serializable;

public class DepartmentManagerId implements Serializable {
    Department department;
    Employee manager;
}
