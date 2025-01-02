package com.project.wma.service;

import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;


@Service
@Validated
@Transactional
@AllArgsConstructor
public class EmployeService1Impl implements EmployeService{
    @Override
    public EmployeResponse addEmploye(Employee emp) {
        return null;
    }

    @Override
    public List<EmployeResponse> findAllEmployes() {
        return null;
    }

    @Override
    public void displayTasks(UUID id) {

    }

}
