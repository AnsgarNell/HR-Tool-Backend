package com.medium.HR.Tool.Backend.model.departmentemployee.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.departmentemployee.DepartmentEmployee;
import com.medium.HR.Tool.Backend.model.employee.Employee;

import java.io.IOException;

/** Class to serialize in JSON format the employees
 *  from a department.
 */
public class DepartmentEmployeeSerializer extends JsonSerializer<DepartmentEmployee> {
    @Override
    public void serialize(DepartmentEmployee departmentEmployee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Employee employee = departmentEmployee.getEmployee();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("empNo", employee.getEmpNo().toString());
        jsonGenerator.writeStringField("firstName", employee.getFirstName());
        jsonGenerator.writeStringField("lastName", employee.getLastName());
        jsonGenerator.writeStringField("fromDate", departmentEmployee.getFromDate().toString());
        jsonGenerator.writeStringField("toDate", departmentEmployee.getToDate().toString());
        jsonGenerator.writeEndObject();
    }
}
