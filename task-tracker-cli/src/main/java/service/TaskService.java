package service;

import org.json.JSONArray;
import org.json.JSONObject;
import storage.JsonUtil;
import model.Task;
import util.TaskConverter;

public class TaskService {
    private static TaskService taskService;
    private final JsonUtil jsonUtil;

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

        JSONArray tasks = jsonObject.getJSONArray("tasks");
        Task task = new Task(tasks.length() + 1, description);
        JSONObject taskJson = TaskConverter.taskToJson(task);
        tasks.put(taskJson);
        jsonObject.put("tasks", tasks);
        jsonUtil.writeJsonObject(jsonObject);
    }
}
