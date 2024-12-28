package com.project.wma.service;

import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;
import com.project.wma.repository.EmployeRepo;
import com.project.wma.utility.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;
import java.util.List;

@Service
@Primary
@Validated
@Transactional
@AllArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepo employeRepo;

    @Override
    public EmployeResponse addEmploye(Employee emp) {
        var saved = employeRepo.save(emp);
        return Mapper.convertToEnity(saved);
    }

    @Override
    public List<EmployeResponse> findAllEmployes() {
        return employeRepo.findAll()
                .stream()
                .map(Mapper::convertToEnity)
                .sorted(Comparator.comparing(EmployeResponse::getNom)
                        .thenComparing(EmployeResponse::getPrenom)).toList();
    }


}
