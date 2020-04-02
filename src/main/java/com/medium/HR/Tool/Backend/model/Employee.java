package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private Integer empNo;

    @Past
    @NotNull(message = "Birth date may not be null")
    @Column(name = "birth_date")
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
    @Column(name = "gender")
    private Gender gender;

    @NotNull(message = "Hire date may not be null")
    @Column(name = "hire_date")
    private Date hireDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "managers")
    private Set<Department> managerOf;

    public Integer getEmpNo() {
        return empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() { return gender; }

    public Date getHireDate() {
        return hireDate;
    }

    public Set<Department> getManagerOf() {
        return managerOf;
    }
}
