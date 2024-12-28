package com.project.wma.service;

import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Validated
@Transactional
@AllArgsConstructor
public class EmployeServiceMock implements EmployeService{
    @Override
    public EmployeResponse addEmploye(Employee emp) {
        return EmployeResponse.builder()
                .nom("wajdi")
                .prenom("mathlouthi")
                .age(31).build();
    }

    @Override
    public List<EmployeResponse> findAllEmployes() {
        return Stream.iterate(0, n -> n + 1)
                .limit(10)
                .map(n -> new EmployeResponse("John", "Doe", 31))
                .collect(Collectors.toList());
    }
}
