package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.auxiliary.DepartmentCreator;
import com.medium.HR.Tool.Backend.auxiliary.DepartmentEmployeeCreator;
import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.department.projections.DepartmentBasicInfo;
import com.medium.HR.Tool.Backend.model.departmentemployee.projections.DepartmentEmployeeBasicInfo;
import com.medium.HR.Tool.Backend.model.employee.projections.EmployeeBasicInfo;
import com.medium.HR.Tool.Backend.model.departmentemployee.repositories.DepartmentEmployeeRepository;
import com.medium.HR.Tool.Backend.model.department.repositories.DepartmentsRepository;
import com.medium.HR.Tool.Backend.model.employee.repositories.EmployeesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureJsonTesters
class DepartmentsControllerTest {

    private final static String URI = "/departments/";

    @Autowired
    private MockMvc mvc;

    @MockBean
    DepartmentsRepository departmentsRepository;

    @MockBean
    DepartmentEmployeeRepository departmentEmployeeRepository;

    @MockBean
    EmployeesRepository employeesRepository;

    @Autowired
    private JacksonTester<Department> jacksonTesterDepartment;

    @Autowired
    private JacksonTester<List<DepartmentBasicInfo>> jacksonTesterDepartmentList;

    @Autowired
    private JacksonTester<List<EmployeeBasicInfo>> jacksonTesterDepartmentEmployeeBasicInfoList;


    @Test
    void getDepartmentList() throws Exception {
        List<DepartmentBasicInfo> departmentBasicInfoList = DepartmentCreator.createDepartmentBasicInfoList();
        given(departmentsRepository.findAllByOrderByDeptNoAsc()).willReturn(departmentBasicInfoList);

        ResultActions resultActions = mvc.perform(get(URI))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String listAsJson = jacksonTesterDepartmentList.write(departmentBasicInfoList).getJson();

        Assertions.assertEquals(listAsJson, contentAsString);
    }

    @Test
    void getNonExistingDepartment() throws Exception {
        given(departmentsRepository.findById(any())).willReturn(Optional.empty());

        mvc.perform(get(URI + "d999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDepartmentWithoutManagerNorEmployees() throws Exception {
        Department department = DepartmentCreator.createDepartment();
        testDepartment(department);
    }

    @Test
    void getDepartmentWithManagerWithoutEmployees() throws Exception {
        Department department = DepartmentCreator.createDepartment();
        DepartmentCreator.createDepartmentManager(department);
        testDepartment(department);
    }

    private void testDepartment(Department department) throws Exception {
        given(departmentsRepository.findById(any())).willReturn(Optional.of(department));

        ResultActions resultActions = mvc.perform(
                get(URI + department.getDeptNo()))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String departmentJson = jacksonTesterDepartment.write(department).getJson();

        Assertions.assertEquals(departmentJson, contentAsString);
    }

    @Test
    void getDepartmentEmptyEmployees() throws Exception {
        Department department = DepartmentCreator.createDepartment();
        given(departmentsRepository.findById(any())).willReturn(Optional.of(department));
        ResultActions resultActions = mvc.perform(
                get(URI + department.getDeptNo() + "/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentEmployees() throws Exception {
        Department department = DepartmentCreator.createDepartment();
        Page<DepartmentEmployeeBasicInfo> employeeBasicInfoPage = DepartmentEmployeeCreator.createPagedDepartmentEmployees(department);
        List<EmployeeBasicInfo> employeeBasicInfoList = new ArrayList<>();
        employeeBasicInfoPage.toList().forEach(departmentEmployeeBasic -> employeeBasicInfoList.add(departmentEmployeeBasic.getEmployee()));
        given(departmentsRepository.findById(any())).willReturn(Optional.of(department));
        given(departmentEmployeeRepository.findAllByDepartment(any(), any())).willReturn(Optional.of(employeeBasicInfoPage));
        ResultActions resultActions = mvc.perform(
                get(URI + department.getDeptNo() + "/employees"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String departmentJson = jacksonTesterDepartmentEmployeeBasicInfoList.write(employeeBasicInfoList).getJson();

        Assertions.assertEquals(departmentJson, contentAsString);
    }
}