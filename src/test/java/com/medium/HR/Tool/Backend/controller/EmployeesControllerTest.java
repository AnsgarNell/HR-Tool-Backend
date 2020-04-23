package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.auxiliary.EmployeeCreator;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentEmployeeRepository;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest
@AutoConfigureJsonTesters
public class EmployeesControllerTest {

    private final static String URI = "/employees/";

    @Autowired
    private MockMvc mvc;

    @MockBean
    EmployeesRepository employeesRepository;

    @MockBean
    DepartmentsRepository departmentsRepository;

    @MockBean
    DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private JacksonTester<Employee> jacksonTesterEmployee;

    @Test
    void getNonExistingEmployee() throws Exception {
        given(employeesRepository.findById(any())).willReturn(Optional.empty());

        mvc.perform(get(URI + "00000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmployeeById() throws Exception {
        Employee employee = EmployeeCreator.createEmployee();
        given(employeesRepository.findById(employee.getEmpNo())).willReturn(Optional.of(employee));

        ResultActions resultActions = mvc.perform(get(URI + employee.getEmpNo()))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String employeeJson = jacksonTesterEmployee.write(employee).getJson();

        Assertions.assertEquals(employeeJson, contentAsString);
    }
}