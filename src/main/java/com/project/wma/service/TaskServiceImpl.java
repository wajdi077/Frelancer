package com.project.wma.service;


import com.project.wma.Exeption.ObjectNotFoundException;
import com.project.wma.domain.Task;
import com.project.wma.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepo taskRepo;

    @Override
    public Task getTask(UUID taskID) throws ObjectNotFoundException {
        return this.taskRepo.findById(taskID).
                orElseThrow(()->new ObjectNotFoundException("object not found",taskID));
    }
}
