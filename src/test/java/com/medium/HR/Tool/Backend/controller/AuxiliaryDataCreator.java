package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.Gender;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuxiliaryDataCreator {

    static public List<Employee> createEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(createEmployee());

        return employeeList;
    }

    static public void createDepartmentManager(Department department) {
        Set<DepartmentManager> departmentManagerSet = new HashSet<>();
        DepartmentManager departmentManager = new DepartmentManager();
        Employee manager = createEmployee();

        departmentManager.setDepartment(department);
        departmentManager.setEmployee(manager);
        departmentManager.setFromDate(Date.valueOf("2000-01-01"));
        departmentManager.setToDate(Date.valueOf("9999-01-01"));

        departmentManagerSet.add(departmentManager);
        department.setManagers(departmentManagerSet);
    }

    static public List<Department> createDepartmentList() {
        List<Department> departmentList = new ArrayList<>();

        departmentList.add(new Department("d001", "Marketing"));
        departmentList.add(new Department("d002", "Finance"));
        departmentList.add(new Department("d003", "Human Resources"));

        return departmentList;
    }

    static public Employee createEmployee () {
        Employee  employee = new Employee();
        employee.setEmpNo(0);
        employee.setFirstName("Test");
        employee.setLastName("Employee");
        employee.setBirthDate(Date.valueOf("1980-01-01"));
        employee.setGender(Gender.M);
        employee.setHireDate(Date.valueOf("2000-01-01"));

        return employee;
    }

    static public Department createDepartment() {
        Department department = new Department("d001", "Marketing");
        return department;
    }
}
