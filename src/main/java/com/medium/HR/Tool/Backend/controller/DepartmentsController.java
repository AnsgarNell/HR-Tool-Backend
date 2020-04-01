package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Departments;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        return departmentsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departments> getDepartmentById(@PathVariable String id) {
        return ResponseEntity.of(departmentsRepository.findById(id));
    }
}
