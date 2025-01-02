package com.project.wma.service;

import com.project.wma.Exeption.ObjectNotFoundException;
import com.project.wma.domain.Task;

import java.util.UUID;

public interface TaskService {

    Task getTask(UUID taskID) throws ObjectNotFoundException;
}
