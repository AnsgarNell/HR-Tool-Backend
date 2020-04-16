package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.*;
import com.medium.HR.Tool.Backend.model.dtos.DepartmentDTO;
import com.medium.HR.Tool.Backend.model.projections.DepartmentBasicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    static public List<DepartmentEmployee> createDepartmentEmployees(Department department) {
        List<DepartmentEmployee> departmentEmployeeList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            DepartmentEmployee departmentEmployee = new DepartmentEmployee();
            departmentEmployee.setDepartment(department);
            Employee  employee = new Employee();
            employee.setEmpNo(i);
            employee.setFirstName(String.format("TestFirsName%02d", i));
            employee.setLastName(String.format("TestLastName %02d", i));
            employee.setBirthDate(Date.valueOf(String.format("1980-%02d-01", i)));
            employee.setGender((i%2 == 0 ? Gender.M : Gender.F));
            employee.setHireDate(Date.valueOf(String.format("2000-%02d-01", i)));
            departmentEmployee.setEmployee(employee);
            departmentEmployee.setFromDate(Date.valueOf(String.format("2000-%02d-01", i)));
            departmentEmployee.setToDate(Date.valueOf(String.format("9999-%02d-01", i)));
            departmentEmployeeList.add(departmentEmployee);
        }
        return departmentEmployeeList;
    }

    static public List<DepartmentEmployee> createDepartmentEmployees(DepartmentDTO departmentDTO) {
        return createDepartmentEmployees(departmentDTO.getDepartment());
    }

    static public Page<DepartmentEmployee> createPagedDepartmentEmployees(Department department) {
        List<DepartmentEmployee> departmentEmployeeList = createDepartmentEmployees(department);
        Pageable pageable = PageRequest.of(0, departmentEmployeeList.size());
        Page<DepartmentEmployee> departmentEmployeePage = new PageImpl<DepartmentEmployee>(departmentEmployeeList, pageable, departmentEmployeeList.size());

        return departmentEmployeePage;
    }

    static public Page<DepartmentEmployee> createPagedDepartmentEmployees(DepartmentDTO departmentDTO) {
        List<DepartmentEmployee> departmentEmployeeList = createDepartmentEmployees(departmentDTO);
        Pageable pageable = PageRequest.of(0, departmentEmployeeList.size());
        Page<DepartmentEmployee> departmentEmployeePage = new PageImpl<DepartmentEmployee>(departmentEmployeeList, pageable, departmentEmployeeList.size());

        return departmentEmployeePage;
    }

    static public Department createDepartment() {
        Department department = new Department("d001", "Marketing");
        return department;
    }

    static public DepartmentDTO createDepartmentDTO() {
        Department department = createDepartment();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartment(department);
        return departmentDTO;
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

    static public void createDepartmentManager(DepartmentDTO departmentDTO) {
        createDepartmentManager(departmentDTO.getDepartment());
    }
}
