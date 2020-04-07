package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.Department;
import com.medium.HR.Tool.Backend.model.DepartmentManager;

import java.io.IOException;
import java.util.Set;

public class ManagedDepartmentsSerializer extends JsonSerializer<Set<DepartmentManager>> {
    @Override
    public void serialize(Set<DepartmentManager> departmentManagerSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (DepartmentManager k : departmentManagerSet) {
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

    @Override
    public boolean isEmpty(SerializerProvider provider, Set<DepartmentManager> value) {
        return (value == null || value.size() == 0);
    }
}
