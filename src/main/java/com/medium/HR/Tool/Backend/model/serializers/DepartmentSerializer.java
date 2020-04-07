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
        jsonGenerator.writeStringField("Department number", department.getDeptNo());
        jsonGenerator.writeStringField("Department name", department.getDeptName());
        if(!department.getManagers().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("Managers");
            for (DepartmentManager k : department.getManagers()) {
                Employee employee = k.getEmployee();
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("Employee number", employee.getEmpNo().toString());
                jsonGenerator.writeStringField("First name", employee.getFirstName());
                jsonGenerator.writeStringField("Last name", employee.getLastName());
                jsonGenerator.writeStringField("From date", k.getFromDate().toString());
                jsonGenerator.writeStringField("To date", k.getToDate().toString());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}
