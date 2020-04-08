package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medium.HR.Tool.Backend.model.serializers.DepartmentEmployeesSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Departments entity implementation
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Table(name = "departments")
public class Department{

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

    @JsonSerialize(using = DepartmentEmployeesSerializer.class)
    @OneToMany(mappedBy = "department")
    @OrderBy("toDate DESC")
    Set<DepartmentManager> managers;

    @JsonSerialize(using = DepartmentEmployeesSerializer.class)
    @OneToMany(mappedBy = "department")
    @OrderBy("toDate DESC")
    Set<DepartmentEmployee> employees;

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

    public Set<DepartmentEmployee> getEmployees() { return employees; }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setManagers(Set<DepartmentManager> managers) {
        this.managers = managers;
    }

    public void setEmployees(Set<DepartmentEmployee> employees) { this.employees = employees; }
}
