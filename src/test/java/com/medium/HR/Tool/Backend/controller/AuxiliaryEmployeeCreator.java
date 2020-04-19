package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.Gender;
import com.medium.HR.Tool.Backend.model.projections.DepartmentBasicInfo;
import com.medium.HR.Tool.Backend.model.projections.EmployeeBasicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AuxiliaryEmployeeCreator {

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

    static public List<Employee> createEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Employee  employee = new Employee();
            employee.setEmpNo(i);
            employee.setFirstName(String.format("TestFirsName%02d", i));
            employee.setLastName(String.format("TestLastName %02d", i));
            employee.setBirthDate(Date.valueOf(String.format("1980-%02d-01", i)));
            employee.setGender((i%2 == 0 ? Gender.M : Gender.F));
            employee.setHireDate(Date.valueOf(String.format("2000-%02d-01", i)));
            employeeList.add(employee);
        }
        return employeeList;
    }

    static public List<EmployeeBasicInfo> createEmployeeBasicInfoList() {
        List<Employee> employeeList = createEmployeeList();
        List<EmployeeBasicInfo> employeeBasicInfoList = new ArrayList<>();

        for(Employee employee : employeeList) {
            EmployeeBasicInfo employeeBasicInfo = AuxiliaryDepartmentEmployeeCreator.factory.createProjection(EmployeeBasicInfo.class, employee);
            employeeBasicInfoList.add(employeeBasicInfo);
        }
        return employeeBasicInfoList;
    }

    static public Page<EmployeeBasicInfo> createPagedEmployeeBasicInfos() {
        List<EmployeeBasicInfo> employeeBasicInfoList = createEmployeeBasicInfoList();
        Pageable pageable = PageRequest.of(0, employeeBasicInfoList.size());
        Page<EmployeeBasicInfo> employeeBasicInfoPage = new PageImpl<EmployeeBasicInfo>(employeeBasicInfoList, pageable, employeeBasicInfoList.size());

        return employeeBasicInfoPage;
    }
}
