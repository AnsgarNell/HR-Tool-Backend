package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.Employee;

import java.io.IOException;
import java.util.List;

public class DepartmentEmployeesSerializer extends JsonSerializer<List<DepartmentEmployee>> {

    @Override
    public void serialize(List<DepartmentEmployee> departmentEmployeeList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (DepartmentEmployee departmentEmployee : departmentEmployeeList) {
            // TODO: Try to somehow call the DepartmentEmployeeSerializer from here to
            // avoid having duplicated code in both serializers
            Employee employee = departmentEmployee.getEmployee();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("empNo", employee.getEmpNo().toString());
            jsonGenerator.writeStringField("firstName", employee.getFirstName());
            jsonGenerator.writeStringField("lastName", employee.getLastName());
            jsonGenerator.writeStringField("fromDate", departmentEmployee.getFromDate().toString());
            jsonGenerator.writeStringField("toDate", departmentEmployee.getToDate().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, List<DepartmentEmployee> value) {
        return (value == null || value.size() == 0);
    }
}
