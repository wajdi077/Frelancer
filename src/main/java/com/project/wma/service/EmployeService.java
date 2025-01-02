package com.project.wma.service;


import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeService {

    EmployeResponse addEmploye(Employee emp);

    List<EmployeResponse> findAllEmployes();

    void displayTasks(UUID id);

    default Optional<Object> deleteAlltasks(){
        return Optional.empty();
    }

}
