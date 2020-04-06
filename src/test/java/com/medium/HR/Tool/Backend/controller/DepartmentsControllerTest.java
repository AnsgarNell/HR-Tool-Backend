package com.medium.HR.Tool.Backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
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

import java.util.Optional;

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

    @MockBean
    EmployeesRepository employeesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JacksonTester<Department> json;

    private Department department;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getNonExistingDepartment() throws Exception {

        given(departmentsRepository.findById(any())).willReturn(Optional.ofNullable(null));

        ResultActions resultActions = mvc.perform(get("/departments/d999"))
                .andExpect(status().isNotFound());

    }

    @Test
    void getDepartmentWithoutManagerNorEmployees() throws Exception {

        department = new Department("d001", "Marketing");
        given(departmentsRepository.findById(any())).willReturn(Optional.of(department));

        ResultActions resultActions = mvc.perform(get("/departments/"+ department.getDeptName()))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        // Extract the department info from the whole JSON tree into a Department type variable
        Department responseDepartment = objectMapper.readValue(
                objectMapper.readTree(contentAsString).findPath("Department").toString(),
                new TypeReference<Department>(){}
            );
        String responseDepartmentJson = json.write(responseDepartment).getJson();
        String departmentJson = json.write(department).getJson();
        assert (departmentJson.equals(responseDepartmentJson));

    }
}