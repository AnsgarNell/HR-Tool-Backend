package com.medium.HR.Tool.Backend.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medium.HR.Tool.Backend.model.departmentemployee.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.departmentmanager.DepartmentManager;
import com.medium.HR.Tool.Backend.model.employee.serializers.EmployeeOfDepartmentsSerializer;
import com.medium.HR.Tool.Backend.model.employee.serializers.ManagerOfDepartmentsSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Employees entity implementation
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "emp_no")
    private Integer empNo;

    @Past
    @NotNull(message = "Birth date may not be null")
    @Column(name = "birth_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @Size(max = 14, message
            = "First name can not contain more than 14 characters")
    @NotNull(message = "First name may not be null")
    @NotEmpty(message = "First name may not be empty")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 16, message
            = "Last name can not contain more than 16 characters")
    @NotNull(message = "Last name may not be null")
    @NotEmpty(message = "Last name may not be empty")
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender may not be null")
    @Column(name = "gender", columnDefinition="ENUM('M','F')")
    private Gender gender;

    @NotNull(message = "Hire date may not be null")
    @Column(name = "hire_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date hireDate;

    @JsonSerialize(using = ManagerOfDepartmentsSerializer.class)
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    List<DepartmentManager> managerOf;

    @JsonSerialize(using = EmployeeOfDepartmentsSerializer.class)
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    List<DepartmentEmployee> employeeOf;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @OrderBy("fromDate DESC")
    List<Title> titles = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @OrderBy("fromDate DESC")
    List<Salary> salaries;

    public Employee() {
    }

    public Employee(Integer empNo, @Past @NotNull(message = "Birth date may not be null") Date birthDate, @Size(max = 14, message
            = "First name can not contain more than 14 characters") @NotNull(message = "First name may not be null") @NotEmpty(message = "First name may not be empty") String firstName, @Size(max = 16, message
            = "Last name can not contain more than 16 characters") @NotNull(message = "Last name may not be null") @NotEmpty(message = "Last name may not be empty") String lastName, @NotNull(message = "Gender may not be null") Gender gender, @NotNull(message = "Hire date may not be null") Date hireDate) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public Gender getGender() { return gender; }

    public Date getHireDate() {
        return hireDate;
    }

    public List<DepartmentManager> getManagerOf() {
        return managerOf;
    }

    public List<DepartmentEmployee> getEmployeeOf() { return employeeOf; }

    public String getCurrentTitle() {
        if (titles.isEmpty()) return "";
        else return titles.get(0).getTitle();
    }

    public List<Title> getTitles() {
        return titles;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setManagerOf(List<DepartmentManager> managerOf) {
        this.managerOf = managerOf;
    }

    public void setEmployeeOf(List<DepartmentEmployee> employeeOf) { this.employeeOf = employeeOf; }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
