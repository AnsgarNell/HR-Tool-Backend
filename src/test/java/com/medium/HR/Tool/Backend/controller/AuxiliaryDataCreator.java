package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.*;
import com.medium.HR.Tool.Backend.model.projections.BasicDepartmentInfo;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AuxiliaryDataCreator {

    static ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

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

    static public List<Department> createDepartmentList() {
        List<Department> departmentList = new ArrayList<>();

        departmentList.add(new Department("d001", "Marketing"));
        departmentList.add(new Department("d002", "Finance"));
        departmentList.add(new Department("d003", "Human Resources"));

        return departmentList;
    }

    static public List<BasicDepartmentInfo> createBasicDepartmentInfoList() {
        List<Department> departmentList = createDepartmentList();
        List<BasicDepartmentInfo> basicDepartmentInfoList = new ArrayList<>();

        for (Department department : departmentList) {
            BasicDepartmentInfo basicDepartmentInfo = factory.createProjection(BasicDepartmentInfo.class, department);
            basicDepartmentInfoList.add(basicDepartmentInfo);
        }

        return basicDepartmentInfoList;
    }

    static public void createDepartmentManager(Department department) {
        List<DepartmentManager> departmentManagerSet = new ArrayList<>();
        DepartmentManager departmentManager = new DepartmentManager();
        Employee manager = createEmployee();

        departmentManager.setDepartment(department);
        departmentManager.setEmployee(manager);
        departmentManager.setFromDate(Date.valueOf("2000-01-01"));
        departmentManager.setToDate(Date.valueOf("9999-01-01"));

        departmentManagerSet.add(departmentManager);
        department.setManagers(departmentManagerSet);
    }
}
