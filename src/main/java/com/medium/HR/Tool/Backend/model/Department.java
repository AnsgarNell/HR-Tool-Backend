package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Departments entity implementation
 */
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "dept_no", columnDefinition = "char")
    private String deptNo;

    @Size(max = 40, message
            = "Department name can not contain more than 40 characters")
    @NotNull(message = "Department name may not be null")
    @NotEmpty(message = "Department name may not be empty")
    @Column(name = "dept_name")
    private String deptName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "dept_manager",
            joinColumns = @JoinColumn(name = "dept_no"),
            inverseJoinColumns = @JoinColumn(name = "emp_no"))
    private Set<Employee> managers;

    public String getDeptNo() {
        return deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public Set<Employee> getManagers() {
        return managers;
    }
}
