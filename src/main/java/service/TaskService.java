package service;

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
    }

    public void update(int id, String newDesc) {
        Task task = jsonUtil.getTasks().get(id);
        task.setDescription(newDesc);
        task.setUpdatedAt(LocalDateTime.now());
        jsonUtil.saveTasks();
    }
}
