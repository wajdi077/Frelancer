package com.project.wma.utility;

import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;

public class Mapper {

    public static EmployeResponse convertToEnity(Employee emp) {
        EmployeResponse response = new EmployeResponse();
        if (emp != null) {
            response.setNom(emp.getNom());
            response.setPrenom(emp.getPrenom());
            response.setAge(emp.getAge());
        }
        return response;
    }
}
