package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.employee.Employee;
import com.medium.HR.Tool.Backend.model.employee.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
