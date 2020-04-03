package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Employee;
import com.medium.HR.Tool.Backend.model.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;

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
     * @param startOrNull Optional parameter that sets the pagination starting point
     * @param limitOrNull Optional parameter that sets the pagination limit
     * @return The list of employees.
     */
    @GetMapping
    public List<?> listEmployees(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer startOrNull,
            @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limitOrNull) {

        Pageable pageable = PageRequest.of(startOrNull, limitOrNull);
        Page<Employee> allEmployees = employeesRepository.findAll(pageable);
        return allEmployees.toList();
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
            map.put("Employee", employee);

            // If the employee is or has been a manager, add also this info
            List<Object> managedDepartments = new ArrayList<>();
            employee.getManagerOf().forEach((k)->{
                List<Object> departmentData = new ArrayList<>();
                departmentData.add(k.getDepartment().getDeptNo());
                departmentData.add(k.getDepartment().getDeptName());
                departmentData.add(k);
                managedDepartments.add(departmentData);
            });
            if(!managedDepartments.isEmpty()) {
                map.put("Managed Departments", managedDepartments);
            }

            return ResponseEntity.ok(map);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}
