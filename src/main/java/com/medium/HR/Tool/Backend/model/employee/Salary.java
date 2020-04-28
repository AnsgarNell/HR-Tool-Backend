package com.medium.HR.Tool.Backend.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(SalaryId.class)
@Table(name = "salaries")
public class Salary implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employee employee;

    @NotNull(message = "Salary may not be null")
    @Column(name = "salary")
    private Integer salary;

    @Id
    @NotNull(message = "From date may not be null")
    @Column(name = "from_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fromDate;

    @NotNull(message = "To date may not be null")
    @Column(name = "to_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date toDate;

    public Employee getEmployee() {
        return employee;
    }

    public Integer getSalary() {
        return salary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
