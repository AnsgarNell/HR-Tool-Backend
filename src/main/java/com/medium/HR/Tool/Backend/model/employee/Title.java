package com.medium.HR.Tool.Backend.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(TitleId.class)
@Table(name = "titles")
public class Title implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employee employee;

    @Id
    @Size(max = 50, message
            = "The title can not contain more than 50 characters")
    @NotNull(message = "The title may not be null")
    @NotEmpty(message = "The title may not be empty")
    @Column(name = "title")
    private String title;

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

    public String getTitle() { return title; }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTitle(String title) { this.title = title; }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
