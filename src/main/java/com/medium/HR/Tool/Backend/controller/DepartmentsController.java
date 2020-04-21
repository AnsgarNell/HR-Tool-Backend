package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.dtos.DepartmentDTO;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentEmployeeRepository;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The DepartmentsController class contains the implementation of
 * the REST Controller for the departments
 */
@RestController
@RequestMapping(value = "/departments")
public class DepartmentsController {

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    DepartmentEmployeeRepository departmentEmployeeRepository;

    /**
     * Lists all departments.
     *
     * @return The list of departments.
     */
    @GetMapping
    public List<?> getDepartments() {
        return departmentsRepository.findAllByOrderByDeptNoAsc();
    }

    /**
     * Returns the information for a specific department.
     *
     * @param id This parameter contains the department identifier
     * @return ResponseEntity<Map<String,Object>> Returns the information in a map
     * separating the department information and the paginated employees information
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @PathVariable String id,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer startOrNull,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limitOrNull) {
        Optional<Department> optionalDepartment = departmentsRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Department department = optionalDepartment.get();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartment(department);

        Pageable pageable = PageRequest.of(startOrNull, limitOrNull);
        Optional<Page<DepartmentEmployee>> optionalDepartmentEmployeePage = departmentEmployeeRepository.findAllByDepartment(department, pageable);
        if (optionalDepartmentEmployeePage.isPresent()) {
            List<DepartmentEmployee> departmentEmployeeList = optionalDepartmentEmployeePage.get().toList();
            departmentDTO.setEmployees(departmentEmployeeList);
        }

        return ResponseEntity.ok(departmentDTO);
    }
}
