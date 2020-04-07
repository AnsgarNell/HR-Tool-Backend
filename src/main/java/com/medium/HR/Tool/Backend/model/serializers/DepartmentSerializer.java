package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;

import java.io.IOException;

public class DepartmentSerializer extends JsonSerializer<Department> {
    @Override
    public void serialize(Department department, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("deptNo", department.getDeptNo());
        jsonGenerator.writeStringField("deptName", department.getDeptName());
        if(!department.getManagers().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("managers");
            for (DepartmentManager k : department.getManagers()) {
                Employee employee = k.getEmployee();
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("empNo", employee.getEmpNo().toString());
                jsonGenerator.writeStringField("firstName", employee.getFirstName());
                jsonGenerator.writeStringField("lastName", employee.getLastName());
                jsonGenerator.writeStringField("fromDate", k.getFromDate().toString());
                jsonGenerator.writeStringField("toDate", k.getToDate().toString());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}
