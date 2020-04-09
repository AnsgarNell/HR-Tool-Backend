package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medium.HR.Tool.Backend.model.serializers.DepartmentEmployeesSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @OrderBy("toDate DESC")
    List<DepartmentManager> managers;

    @JsonSerialize(using = DepartmentEmployeesSerializer.class)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @OrderBy("toDate DESC")
    List<DepartmentEmployee> employees;

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

    public List<DepartmentManager> getManagers() {
        return managers;
    }

    public List<DepartmentEmployee> getEmployees() { return employees; }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setManagers(List<DepartmentManager> managers) {
        this.managers = managers;
    }

    public void setEmployees(List<DepartmentEmployee> employees) { this.employees = employees; }
}
