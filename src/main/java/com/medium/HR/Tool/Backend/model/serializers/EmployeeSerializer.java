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
        jsonGenerator.writeStringField("empNo", employee.getEmpNo().toString());
        jsonGenerator.writeStringField("firstName", employee.getFirstName());
        jsonGenerator.writeStringField("lastName", employee.getLastName());
        jsonGenerator.writeStringField("birthDate", employee.getBirthDate().toString());
        jsonGenerator.writeStringField("gender", employee.getGender().toString());
        jsonGenerator.writeStringField("hireDate", employee.getHireDate().toString());
        if(!employee.getManagerOf().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("managerOf");
            for (DepartmentManager k : employee.getManagerOf()) {
                Department department = k.getDepartment();
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("deptNo", department.getDeptNo());
                jsonGenerator.writeStringField("deptName", department.getDeptName());
                jsonGenerator.writeStringField("fromDate", k.getFromDate().toString());
                jsonGenerator.writeStringField("toDate", k.getToDate().toString());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}
