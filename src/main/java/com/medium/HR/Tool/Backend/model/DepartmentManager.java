package com.medium.HR.Tool.Backend.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medium.HR.Tool.Backend.model.serializers.EmployeeOfDepartmentsSerializer;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dept_manager")
@JsonSerialize(using = EmployeeOfDepartmentsSerializer.class)
public class DepartmentManager extends DepartmentEmployee {
}
