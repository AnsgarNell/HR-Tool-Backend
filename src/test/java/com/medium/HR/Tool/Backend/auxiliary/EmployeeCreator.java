package com.medium.HR.Tool.Backend.auxiliary;

import com.medium.HR.Tool.Backend.model.employee.Employee;
import com.medium.HR.Tool.Backend.model.employee.Gender;

import java.sql.Date;

public class EmployeeCreator {

    static public Employee createEmployee () {
        Employee  employee = new Employee();
        employee.setEmpNo(1);
        employee.setFirstName("Test");
        employee.setLastName("Employee");
        employee.setBirthDate(Date.valueOf("1980-01-01"));
        employee.setGender(Gender.M);
        employee.setHireDate(Date.valueOf("2000-01-01"));

        return employee;
    }
}
