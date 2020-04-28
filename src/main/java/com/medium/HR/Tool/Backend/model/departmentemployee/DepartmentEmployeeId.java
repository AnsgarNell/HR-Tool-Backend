package com.medium.HR.Tool.Backend.model.departmentemployee;

import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.employee.Employee;

import java.io.Serializable;

public class DepartmentEmployeeId implements Serializable {
    Department department;
    Employee employee;
}
