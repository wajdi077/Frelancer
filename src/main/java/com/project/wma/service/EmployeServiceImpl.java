package com.project.wma.service;

import com.project.wma.Response.EmployeResponse;
import com.project.wma.domain.Employee;
import com.project.wma.domain.Task;
import com.project.wma.repository.EmployeRepo;
import com.project.wma.utility.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Primary
@Validated
@Transactional
@AllArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepo employeRepo;

    @Override
    @Caching(value = "employees")
    @Timed
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

    @Override
    public void displayTasks(UUID id) {
        Employee emp = getEmploye(id);
        List<Task> tasks = emp.getTasks();
        tasks.forEach((t) -> System.out.print(t.getTaskName()));
    }

    private Employee getEmploye(UUID id) {
        return employeRepo.findById(id).orElseThrow(() -> new RuntimeException("not exist"));
    }

    public void deleteAlltasks(UUID empID) {
      this.employeRepo.deleteAllById(List.of(empID));
    }


}
