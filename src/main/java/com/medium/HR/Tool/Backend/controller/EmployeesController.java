package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.projections.EmployeeBasicInfo;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
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
 * The EmployeesController class contains the implementation of
 * the REST Controller for the employees
 */
@RestController
@Validated
@RequestMapping(value = "/employees")
public class EmployeesController {

    @Autowired
    EmployeesRepository employeesRepository;

    /**
     * Lists all employees.
     *
     * @param startOrNull Optional parameter that sets the pagination starting point
     * @param limitOrNull Optional parameter that sets the pagination limit
     * @return The list of employees.
     */
    @GetMapping
    public ResponseEntity<List<?>> getEmployees(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer startOrNull,
            @RequestParam(value = "limit", required = false, defaultValue = "50") @Valid @Max(100) Integer limitOrNull) {
        Pageable pageable = PageRequest.of(startOrNull, limitOrNull);
        Page<EmployeeBasicInfo> allEmployees = employeesRepository.findAllByOrderByEmpNoAsc(pageable);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(allEmployees.getTotalElements()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(allEmployees.toList());
    }

    /**
     * Returns the information for a specific employee.
     *
     * @param id This parameter contains the employee identifier
     * @return ResponseEntity<Map<String,Object>> Returns the information in a map
     * separating the employee information and the departments it has managed information
     * (if any) to avoid infinite JSON loops.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeesRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // TODO: Hide or get salaries information based on permissions.
        Employee employee = optionalEmployee.get();
        return ResponseEntity.ok(employee);
    }
}
