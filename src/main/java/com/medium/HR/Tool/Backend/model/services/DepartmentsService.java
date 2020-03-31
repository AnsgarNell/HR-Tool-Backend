package com.medium.HR.Tool.Backend.model.services;

import com.medium.HR.Tool.Backend.model.Departments;
import com.medium.HR.Tool.Backend.model.repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsService {

    @Autowired
    DepartmentsRepository repository;

    /**
     * Gathers a list of all departments
     * @return a list of all departments in the DepartmentsRepository
     */
    public List<Departments> list() {
        return repository.findAll();
    }
}
