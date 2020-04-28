package com.medium.HR.Tool.Backend.auxiliary;

import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.departmentemployee.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.employee.Employee;
import com.medium.HR.Tool.Backend.model.employee.Title;
import com.medium.HR.Tool.Backend.model.departmentemployee.projections.DepartmentEmployeeBasicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.List;

public class DepartmentEmployeeCreator {

    static ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    static public List<DepartmentEmployeeBasicInfo> createDepartmentEmployees(Department department) {
        List<DepartmentEmployeeBasicInfo> departmentEmployeeBasicInfoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            DepartmentEmployee departmentEmployee = new DepartmentEmployee();
            departmentEmployee.setDepartment(department);
            Employee  employee = new Employee();
            employee.setEmpNo(i);
            employee.setFirstName(String.format("TestFirsName%02d", i));
            employee.setLastName(String.format("TestLastName %02d", i));
            createTitles(employee);
            departmentEmployee.setEmployee(employee);
            DepartmentEmployeeBasicInfo departmentEmployeeBasicInfo = factory.createProjection(DepartmentEmployeeBasicInfo.class, departmentEmployee);
            departmentEmployeeBasicInfoList.add(departmentEmployeeBasicInfo);
        }
        return departmentEmployeeBasicInfoList;
    }

    private static void createTitles(Employee employee) {
        List<Title> titleList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Title title = new Title();
            title.setTitle(String.format("TestTitle%02d", i));
            titleList.add(title);
        }
        employee.setTitles(titleList);
    }

    static public Page<DepartmentEmployeeBasicInfo> createPagedDepartmentEmployees(Department department) {
        List<DepartmentEmployeeBasicInfo> departmentEmployeeBasicInfoList;
        departmentEmployeeBasicInfoList = createDepartmentEmployees(department);
        Pageable pageable = PageRequest.of(0, departmentEmployeeBasicInfoList.size());
        return new PageImpl<>(
                departmentEmployeeBasicInfoList, pageable, departmentEmployeeBasicInfoList.size());
    }
}
