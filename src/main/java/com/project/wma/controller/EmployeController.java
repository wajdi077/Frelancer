package com.project.wma.controller;


import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;
import com.project.wma.service.EmployeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeController {

    private final EmployeService service;

    @PostMapping("/")
    public ResponseEntity<EmployeResponse> saveEmploye(@Validated @RequestBody Employee emp){
        var result = service.addEmploye(emp);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeResponse>> getEmployes(){
        return ResponseEntity.ok(service.findAllEmployes());
    }




}
