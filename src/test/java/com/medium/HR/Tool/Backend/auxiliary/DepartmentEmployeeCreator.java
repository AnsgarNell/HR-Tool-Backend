package com.medium.HR.Tool.Backend.auxiliary;

import com.medium.HR.Tool.Backend.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DepartmentEmployeeCreator {

    static ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

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

    static public Page<DepartmentEmployee> createPagedDepartmentEmployees(Department department) {
        List<DepartmentEmployee> departmentEmployeeList = createDepartmentEmployees(department);
        Pageable pageable = PageRequest.of(0, departmentEmployeeList.size());
        Page<DepartmentEmployee> departmentEmployeePage = new PageImpl<DepartmentEmployee>(departmentEmployeeList, pageable, departmentEmployeeList.size());

        return departmentEmployeePage;
    }
}
