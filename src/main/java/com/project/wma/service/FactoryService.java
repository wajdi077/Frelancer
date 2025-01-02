package com.project.wma.service;


import com.project.wma.domain.Task;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Service
@Transactional
@Validated
@AllArgsConstructor
public class FactoryService {

    private final systematic systematic;

    public void displayEmploye(@NonNull Task task) {
        var systematic = this.systematic.getService(task);
        var employes = systematic.findAllEmployes();
    }

}
