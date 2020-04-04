package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    DepartmentsRepository departmentsRepository;

    @BeforeEach
    void setUp() {

        Department department = new Department("d001", "Marketing");

        given(departmentsRepository.findById(any())).willReturn(Optional.of(department));

    }

    @Test
    void getDepartmentWithoutManagerNorEmployees() throws Exception {

        ResultActions resultActions = mvc.perform(get("/departments/d001"))
                .andExpect(status().isOk());

    }
}