package com.medium.HR.Tool.Backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DepartmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    DepartmentsRepository departmentsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JacksonTester<Department> jacksonTesterDepartment;

    @Autowired
    private JacksonTester<List<Department>> jacksonTesterDepartmentList;

    @Autowired
    private JacksonTester<Set<DepartmentManager>> jacksonTesterDepartmentManagerSet;


    @Test
    void getDepartmentList() throws Exception {

        List<Department> departmentList = AuxiliaryDataCreator.createDepartmentList();
        given(departmentsRepository.findAllByOrderByDeptNoAsc()).willReturn(departmentList);

        ResultActions resultActions = mvc.perform(get("/departments"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String departmentListAsJson = jacksonTesterDepartmentList.write(departmentList).getJson();

        Assert.assertEquals(departmentListAsJson, contentAsString);
    }

    @Test
    void getNonExistingDepartment() throws Exception {

        given(departmentsRepository.findById(any())).willReturn(Optional.ofNullable(null));

        mvc.perform(get("/departments/d999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDepartmentWithoutManagerNorEmployees() throws Exception {
        Department department = AuxiliaryDataCreator.createDepartment();
        testDepartment(department);
    }

    @Test
    void getDepartmentWithManagerWithoutEmployees() throws Exception {
        Department department = AuxiliaryDataCreator.createDepartment();
        AuxiliaryDataCreator.createDepartmentManager(department);
        testDepartment(department);
    }

    private void testDepartment(Department department) throws Exception {
        given(departmentsRepository.findById(any())).willReturn(Optional.of(department));

        ResultActions resultActions = mvc.perform(
                get("/departments/"+ department.getDeptName()))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String departmentJson = jacksonTesterDepartment.write(department).getJson();

        Assert.assertEquals(departmentJson, contentAsString);
    }
}