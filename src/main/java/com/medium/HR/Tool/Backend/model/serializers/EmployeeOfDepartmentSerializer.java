package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;

import java.io.IOException;

public class EmployeeOfDepartmentSerializer extends JsonSerializer<DepartmentEmployee> {
    @Override
    public void serialize(DepartmentEmployee departmentEmployee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Department department = departmentEmployee.getDepartment();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("deptNo", department.getDeptNo());
        jsonGenerator.writeStringField("deptName", department.getDeptName());
        jsonGenerator.writeStringField("fromDate", departmentEmployee.getFromDate().toString());
        jsonGenerator.writeStringField("toDate", departmentEmployee.getToDate().toString());
        jsonGenerator.writeEndObject();
    }
}
