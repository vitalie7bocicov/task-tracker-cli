package service;

import model.Operation;
import model.Status;
import model.Task;
import storage.JsonUtil;

import java.time.LocalDateTime;

public class TaskService {
    private static TaskService taskService;
    private final JsonUtil jsonUtil;

    private TaskService(JsonUtil jsonUtil){
        this.jsonUtil = jsonUtil;
    }

    public static TaskService getInstance() {
        if (taskService == null) {
            taskService = new TaskService(new JsonUtil());
        }
        return taskService;
    }

    public void add(String description) {
        Task task = new Task(description);
        int id = jsonUtil.saveTask(task);
        System.out.printf("Task added successfully (ID: %d)", id);
    }

    public void list() {
        jsonUtil.getTasks().forEach(
                (id, task) -> {
                    System.out.println(task);
                }
        );
    }

    public void listByStatus(Status status) {
        jsonUtil.getTasks().entrySet()
                .stream()
                .filter(entry ->
                    entry.getValue().getStatus() == status)
                .forEach((entry) -> {
                    System.out.println(entry.getValue());
                });
    }

    public void update(int id, String newDesc) {
        Task task = jsonUtil.getTasks().get(id);
        if (task == null) {
            throw new RuntimeException("Task with this id does not exit");
        }
        task.setDescription(newDesc);
        task.setUpdatedAt(LocalDateTime.now());
        jsonUtil.saveTasks();
    }

    public void delete(int id) {
        jsonUtil.getTasks().remove(id);
        jsonUtil.saveTasks();
    }

    public void markStatus(int id, Status status) {
        Task task = jsonUtil.getTasks().get(id);
        if (task == null) {
            throw new RuntimeException("Task with this id does not exit");
        }
        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());
        jsonUtil.saveTasks();
    }
}
