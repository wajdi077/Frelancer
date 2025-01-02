package com.project.wma.service;


import com.project.wma.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class systematic {

    @Lazy
    private final EmployeServiceImpl employeService;
    @Lazy
    private final EmployeService1Impl employeService1;

    public EmployeService getService(Task task) {
        ValidityType validityType = ValidityType.valueOf(task.getTaskName().toUpperCase());

        return switch (validityType) {
            case PRNUMBERS -> employeService;
            case VARIANTS -> employeService1;
        };
    }

}
