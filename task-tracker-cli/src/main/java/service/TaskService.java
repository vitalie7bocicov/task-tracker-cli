package service;

import org.json.JSONObject;
import storage.JsonUtil;
import storage.Task;

public class TaskService {
    private static TaskService taskService;
    private JsonUtil jsonUtil;

    private TaskService() {
        jsonUtil = JsonUtil.getInstance();
    }

    public static TaskService getInstance() {
        if (taskService == null) {
            taskService = new TaskService();
        }
        return taskService;
    }

    public void addTask(String description) {
        JSONObject jsonObject = jsonUtil.getContent();
        if (jsonObject == null) {
            return;
        }

        
    }
}
