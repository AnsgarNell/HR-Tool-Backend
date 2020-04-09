package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class EmployeeOfDepartmentsSerializer extends JsonSerializer<List<DepartmentEmployee>> {

    @Autowired
    EmployeeOfDepartmentSerializer employeeOfDepartmentSerializer;

    @Override
    public void serialize(List<DepartmentEmployee> departmentEmployeeList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (DepartmentEmployee departmentEmployee : departmentEmployeeList) {
            employeeOfDepartmentSerializer.serialize(departmentEmployee, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, List<DepartmentEmployee> value) {
        return (value == null || value.size() == 0);
    }
}
