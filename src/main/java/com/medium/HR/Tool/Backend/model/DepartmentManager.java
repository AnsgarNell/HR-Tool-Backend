package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dept_manager")
public class DepartmentManager implements Serializable {

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dept_no")
    Department department;

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no")
    Employee employee;

    @NotNull(message = "From date may not be null")
    @Column(name = "from_date")
    private Date fromDate;

    @NotNull(message = "To date may not be null")
    @Column(name = "to_date")
    private Date toDate;

    public Department getDepartment() {
        return department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
}
