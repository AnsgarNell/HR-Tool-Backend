package com.medium.HR.Tool.Backend.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medium.HR.Tool.Backend.model.DepartmentManager;
import com.medium.HR.Tool.Backend.model.Employee;

import java.io.IOException;
import java.util.List;

/** Class to serialize in JSON format the managers
 *  from a department.
 */
public class DepartmentManagersSerializer extends JsonSerializer<List<DepartmentManager>> {

    @Override
    public void serialize(List<DepartmentManager> departmentManagersList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (DepartmentManager departmentManager : departmentManagersList) {
            // TODO: Try to somehow call the DepartmentEmployeeSerializer from here to
            // avoid having duplicated code in both serializers
            Employee employee = departmentManager.getManager();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("empNo", employee.getEmpNo().toString());
            jsonGenerator.writeStringField("firstName", employee.getFirstName());
            jsonGenerator.writeStringField("lastName", employee.getLastName());
            jsonGenerator.writeStringField("fromDate", departmentManager.getFromDate().toString());
            jsonGenerator.writeStringField("toDate", departmentManager.getToDate().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, List<DepartmentManager> value) {
        return (value == null || value.size() == 0);
    }
}
