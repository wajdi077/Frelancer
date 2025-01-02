package com.project.wma.controller;

import com.project.wma.Exeption.ObjectNotFoundException;
import com.project.wma.domain.Task;
import com.project.wma.service.TaskService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskResponse> getTask(@RequestParam UUID id) throws ObjectNotFoundException {
        var task = taskService.getTask(id);
        if (task == null) {
            throw new ObjectNotFoundException("Task", id);
        }
        return ResponseEntity.ok(convertToResponse(task));
    }

    private TaskResponse convertToResponse(Task task) {
        return TaskResponse.builder()
                .taskName(task.getTaskName())
                .description(task.getDescription())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class TaskResponse {
        private String taskName;
        private String description;
    }
}
