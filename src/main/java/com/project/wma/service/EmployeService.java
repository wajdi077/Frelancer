package com.project.wma.service;


import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeService {

    EmployeResponse addEmploye(Employee emp);

    List<EmployeResponse> findAllEmployes();
}
