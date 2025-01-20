package service;

import model.Task;
import storage.JsonUtil;

import java.util.ArrayList;
import java.util.List;

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
        jsonUtil.saveTask(task);
    }

    public void list() {
    }
}
