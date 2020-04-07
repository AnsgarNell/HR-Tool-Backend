package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;

import java.io.IOException;

public class EmployeeSerializer extends JsonSerializer<Employee> {
    @Override
    public void serialize(Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("Employee number", employee.getEmpNo().toString());
        jsonGenerator.writeStringField("First name", employee.getFirstName());
        jsonGenerator.writeStringField("Last name", employee.getLastName());
        jsonGenerator.writeStringField("Birth date", employee.getBirthDate().toString());
        jsonGenerator.writeStringField("Gender", employee.getGender().toString());
        jsonGenerator.writeStringField("Hire date", employee.getHireDate().toString());
        if(!employee.getManagerOf().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("Managed departments");
            for (DepartmentManager k : employee.getManagerOf()) {
                Department department = k.getDepartment();
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("Department number", department.getDeptNo());
                jsonGenerator.writeStringField("Department name", department.getDeptName());
                jsonGenerator.writeStringField("From date", k.getFromDate().toString());
                jsonGenerator.writeStringField("To date", k.getToDate().toString());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}
