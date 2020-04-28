package com.medium.HR.Tool.Backend.model.employee.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.department.Department;
import com.medium.HR.Tool.Backend.model.departmentmanager.DepartmentManager;

import java.io.IOException;
import java.util.List;

/** Class to serialize in JSON format the departments
 *  which an employee has managed.
 */
public class ManagerOfDepartmentsSerializer extends JsonSerializer<List<DepartmentManager>> {
    @Override
    public void serialize(List<DepartmentManager> managedDepartmentsList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (DepartmentManager k : managedDepartmentsList) {
            // TODO: Try to somehow call the EmployeeOfDepartmentSerializer from here to
            // avoid having duplicated code in both serializers
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
    public boolean isEmpty(SerializerProvider provider, List<DepartmentManager> value) {
        return (value == null || value.size() == 0);
    }
}
