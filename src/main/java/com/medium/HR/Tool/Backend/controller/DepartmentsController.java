package com.medium.HR.Tool.Backend.controller;

import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
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
        return departmentsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getDepartmentById(@PathVariable String id) {
        Optional<Department> optionalDepartment = departmentsRepository.findById(id);

        if(optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            Map<String,Object> map = new HashMap<>();
            map.put("Department", department);
            map.put("Managers", department.getManagers());
            return ResponseEntity.ok(map);
        }
        else throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}
