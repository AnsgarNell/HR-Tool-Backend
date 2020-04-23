package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentEmployeeRepository;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

/**
 * The DepartmentsController class contains the implementation of
 * the REST Controller for the departments
 */
@RestController
@Validated
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
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {
        Optional<Department> optionalDepartment = departmentsRepository.findById(id);

        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Department department = optionalDepartment.get();
        return ResponseEntity.ok(department);
    }

    /**
     * Lists all employees.
     *
     * @param startOrNull Optional parameter that sets the pagination starting point
     * @param limitOrNull Optional parameter that sets the pagination limit
     * @return The list of employees.
     */
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<DepartmentEmployee>> getEmployeesByDepartment(
            @PathVariable String id,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer startOrNull,
            @RequestParam(value = "limit", required = false, defaultValue = "20") @Valid @Max(100) Integer limitOrNull) {
        Optional<Department> optionalDepartment = departmentsRepository.findById(id);

        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Department department = optionalDepartment.get();
        Pageable pageable = PageRequest.of(startOrNull, limitOrNull);
        Optional<Page<DepartmentEmployee>> optionalDepartmentEmployeePage = departmentEmployeeRepository.findAllByDepartment(department, pageable);
        if (!optionalDepartmentEmployeePage.isPresent()) {
            return ResponseEntity.ok().build();
        }

        Page<DepartmentEmployee> departmentEmployeePage = optionalDepartmentEmployeePage.get();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(departmentEmployeePage.getTotalElements()));
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(departmentEmployeePage.toList());
    }
}
