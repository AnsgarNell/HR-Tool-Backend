package com.medium.HR.Tool.Backend.auxiliary;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.projections.DepartmentBasicInfo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCreator {

    static public Department createDepartment() {
        return new Department("d001", "Marketing");
    }

    static public List<Department> createDepartmentList() {
        List<Department> departmentList = new ArrayList<>();

        departmentList.add(new Department("d001", "Marketing"));
        departmentList.add(new Department("d002", "Finance"));
        departmentList.add(new Department("d003", "Human Resources"));

        return departmentList;
    }

    static public List<DepartmentBasicInfo> createDepartmentBasicInfoList() {
        List<Department> departmentList = createDepartmentList();
        List<DepartmentBasicInfo> departmentBasicInfoList = new ArrayList<>();

        for (Department department : departmentList) {
            DepartmentBasicInfo departmentBasicInfo = DepartmentEmployeeCreator.factory.createProjection(DepartmentBasicInfo.class, department);
            departmentBasicInfoList.add(departmentBasicInfo);
        }

        return departmentBasicInfoList;
    }

    static public void createDepartmentManager(Department department) {
        List<DepartmentManager> departmentManagerSet = new ArrayList<>();
        DepartmentManager departmentManager = new DepartmentManager();
        Employee manager = EmployeeCreator.createEmployee();

        departmentManager.setDepartment(department);
        departmentManager.setEmployee(manager);
        departmentManager.setFromDate(Date.valueOf("2000-01-01"));
        departmentManager.setToDate(Date.valueOf("9999-01-01"));

        departmentManagerSet.add(departmentManager);
        department.setManagers(departmentManagerSet);
    }
}
