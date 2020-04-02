package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The EmployeesController class contains the implementation of
 * the REST Controller for the employees
 */
@RestController
@RequestMapping(value = "/employees")
public class EmployeesController {

    @Autowired
    EmployeesRepository employeesRepository;

    /**
     * Lists all employees.
     *
     * @return The list of employees.
     */
    @GetMapping
    public List<?> listEmployees() {
        return employeesRepository.findAll();
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
    public ResponseEntity<Map<String,Object>> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeesRepository.findById(id);

        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            Map<String,Object> map = new HashMap<>();
            map.put(employee.getClass().getSimpleName(), employee);

            return ResponseEntity.ok(map);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}
