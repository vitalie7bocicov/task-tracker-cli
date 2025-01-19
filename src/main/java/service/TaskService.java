package service;

import model.Task;
import storage.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static TaskService taskService;
    private final JsonUtil jsonUtil;
    List<Task> tasks = new ArrayList<>();

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
        Task task = new Task(1, description);
        jsonUtil.saveTask(task);
    }

    public void list() {
    }
}
