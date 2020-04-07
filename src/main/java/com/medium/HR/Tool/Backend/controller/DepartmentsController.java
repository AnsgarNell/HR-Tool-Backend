package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * The DepartmentsController class contains the implementation of
 * the REST Controller for the departments
 */
@RestController
@RequestMapping(value = "/departments")
public class DepartmentsController {

    @Autowired
    DepartmentsRepository departmentsRepository;

    /**
     * Lists all departments.
     *
     * @return The list of departments.
     */
    @GetMapping
    public List<?> listDepartments() {
        return departmentsRepository.findAllByOrderByDeptNoAsc();
    }

    /**
     * Returns the information for a specific department.
     *
     * @param id This parameter contains the department identifier
     * @return ResponseEntity<Map<String,Object>> Returns the information in a map
     * separating the department information and the managers information to avoid
     * infinite JSON loops.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {
        Optional<Department> optionalDepartment = departmentsRepository.findById(id);

        if(!optionalDepartment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Department department = optionalDepartment.get();
        return ResponseEntity.ok(department);
    }
}
