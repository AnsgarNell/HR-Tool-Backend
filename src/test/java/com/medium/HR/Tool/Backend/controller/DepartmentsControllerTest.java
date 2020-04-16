package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.dtos.DepartmentDTO;
import com.medium.HR.Tool.Backend.model.projections.DepartmentBasicInfo;
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
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureJsonTesters
class DepartmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    DepartmentsRepository departmentsRepository;

    @MockBean
    DepartmentEmployeeRepository departmentEmployeeRepository;

    @MockBean
    EmployeesRepository employeesRepository;

    @Autowired
    private JacksonTester<DepartmentDTO> departmentDTOJacksonTester;

    @Autowired
    private JacksonTester<List<DepartmentBasicInfo>> jacksonTesterDepartmentList;


    @Test
    void getDepartmentList() throws Exception {
        List<DepartmentBasicInfo> departmentBasicInfoList = AuxiliaryDataCreator.createBasicDepartmentInfoList();
        given(departmentsRepository.findAllByOrderByDeptNoAsc()).willReturn(departmentBasicInfoList);

        ResultActions resultActions = mvc.perform(get("/departments"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String departmentListAsJson = jacksonTesterDepartmentList.write(departmentBasicInfoList).getJson();

        Assertions.assertEquals(departmentListAsJson, contentAsString);
    }

    @Test
    void getNonExistingDepartment() throws Exception {
        given(departmentsRepository.findById(any())).willReturn(Optional.ofNullable(null));

        mvc.perform(get("/departments/d999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDepartmentWithoutManagerNorEmployees() throws Exception {
        DepartmentDTO departmentDTO = AuxiliaryDataCreator.createDepartmentDTO();
        testDepartment(departmentDTO);
    }

    @Test
    void getDepartmentWithManagerWithoutEmployees() throws Exception {
        DepartmentDTO departmentDTO = AuxiliaryDataCreator.createDepartmentDTO();
        AuxiliaryDataCreator.createDepartmentManager(departmentDTO);
        testDepartment(departmentDTO);
    }

    @Test
    void getDepartmentWithManagerAndEmployees() throws Exception {
        DepartmentDTO departmentDTO = AuxiliaryDataCreator.createDepartmentDTO();
        AuxiliaryDataCreator.createDepartmentManager(departmentDTO);
        Page<DepartmentEmployee> departmentEmployeePage = AuxiliaryDataCreator.createPagedDepartmentEmployees(departmentDTO);
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeePage.toList();
        departmentDTO.setEmployees(departmentEmployeeList);
        given(departmentEmployeeRepository.findAllByDepartment(any(), any())).willReturn(Optional.of(departmentEmployeePage));
        testDepartment(departmentDTO);
    }

    private void testDepartment(DepartmentDTO departmentDTO) throws Exception {
        given(departmentsRepository.findById(any())).willReturn(Optional.of(departmentDTO.getDepartment()));

        ResultActions resultActions = mvc.perform(
                get("/departments/"+ departmentDTO.getDepartment().getDeptName()));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String departmentJson = departmentDTOJacksonTester.write(departmentDTO).getJson();

        Assertions.assertEquals(departmentJson, contentAsString);
    }
}