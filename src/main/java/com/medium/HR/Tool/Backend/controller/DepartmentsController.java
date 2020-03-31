package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.services.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentsController {

    @Autowired
    DepartmentsService departmentsService;

    /**
     * Lists all departments.
     *
     * @return The list of departments.
     */
    @GetMapping
    public List<?> listDepartments() {
        return departmentsService.list();
    }
}
