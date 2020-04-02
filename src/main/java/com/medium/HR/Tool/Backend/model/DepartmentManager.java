package com.medium.HR.Tool.Backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dept_manager")
public class DepartmentManager implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "dept_no")
    Department department;

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no")
    Employee employee;

    @NotNull(message = "From date may not be null")
    @Column(name = "from_date")
    private Date fromDate;

    @NotNull(message = "To date may not be null")
    @Column(name = "to_date")
    private Date toDate;
}
