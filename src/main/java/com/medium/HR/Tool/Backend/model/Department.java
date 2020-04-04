package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Departments entity implementation
 */
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "dept_no", columnDefinition = "char(4)")
    private String deptNo;

    @Size(max = 40, message
            = "Department name can not contain more than 40 characters")
    @NotNull(message = "Department name may not be null")
    @NotEmpty(message = "Department name may not be empty")
    @Column(name = "dept_name")
    private String deptName;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    @OrderBy("toDate DESC")
    Set<DepartmentManager> managers = new HashSet<>();

    public Department() {
    }

    public Department(String deptNo, @Size(max = 40, message
            = "Department name can not contain more than 40 characters") @NotNull(message = "Department name may not be null") @NotEmpty(message = "Department name may not be empty") String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public Set<DepartmentManager> getManagers() {
        return managers;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setManagers(Set<DepartmentManager> managers) {
        this.managers = managers;
    }
}
