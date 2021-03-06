package com.medium.HR.Tool.Backend.model.departmentemployee;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.employee.Employee;
import com.medium.HR.Tool.Backend.model.departmentemployee.serializers.DepartmentEmployeeSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(DepartmentEmployeeId.class)
@Table(name = "dept_emp")
@JsonSerialize(using = DepartmentEmployeeSerializer.class)
public class DepartmentEmployee implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no")
    private Department department;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    private Employee employee;

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

    public void setDepartment(Department department) {
        this.department = department;
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
}
