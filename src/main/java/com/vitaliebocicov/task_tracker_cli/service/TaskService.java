package com.vitaliebocicov.task_tracker_cli.service;

import com.vitaliebocicov.task_tracker_cli.model.Status;
import com.vitaliebocicov.task_tracker_cli.model.Task;
import com.vitaliebocicov.task_tracker_cli.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void add(String description) {
        Task task = new Task(description);
        Task savedTask = repository.save(task);
        System.out.println("Task added successfully (ID: " + savedTask.getId() + ")");
    }

    public void list() {
        repository.getTasks().forEach(System.out::println);
    }

    public void listByStatus(Status status) {
        List<Task> filteredTasks = repository.getTasks().stream()
                .filter(task -> task.getStatus() == status)
                .toList();

        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found with status: " + status);
        } else {
            filteredTasks.forEach(System.out::println);
        }
    }

    public void updateDescription(int id, String newDesc) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with this id not found."));
        task.setDescription(newDesc);
        task.setUpdatedAt(LocalDateTime.now());
        repository.save(task);
    }

    public void markStatus(int id, Status status) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with this id not found."));
        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());
        repository.save(task);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
