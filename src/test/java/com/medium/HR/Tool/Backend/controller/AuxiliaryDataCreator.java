package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.Gender;
import com.medium.HR.Tool.Backend.model.projections.DepartmentBasicInfo;
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

    static public List<DepartmentBasicInfo> createBasicDepartmentInfoList() {
        List<Department> departmentList = createDepartmentList();
        List<DepartmentBasicInfo> departmentBasicInfoList = new ArrayList<>();

        for (Department department : departmentList) {
            DepartmentBasicInfo departmentBasicInfo = factory.createProjection(DepartmentBasicInfo.class, department);
            departmentBasicInfoList.add(departmentBasicInfo);
        }

        return departmentBasicInfoList;
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
