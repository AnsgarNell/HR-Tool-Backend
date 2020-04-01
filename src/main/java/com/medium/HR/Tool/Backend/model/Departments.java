package com.medium.HR.Tool.Backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "departments")
public class Departments {

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

    public String getDeptNo() {
        return deptNo;
    }

    public String getDeptName() {
        return deptName;
    }
}
