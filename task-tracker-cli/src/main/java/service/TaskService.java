package service;

import org.json.JSONArray;
import org.json.JSONObject;
import storage.JsonUtil;
import model.Task;
import util.TaskConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public void deleteTask(int id) {

        JSONObject jsonObject = jsonUtil.getContent();
        if (jsonObject == null) {
            return;
        }

        JSONArray tasks = jsonObject.getJSONArray("tasks");

        for (int i = 0; i < tasks.length(); i++) {
            JSONObject task = tasks.getJSONObject(i);
            if (task.getInt("id") == id) {
                tasks.remove(i);
                break;
            }
        }

        jsonObject.put("tasks", tasks);
        jsonUtil.writeJsonObject(jsonObject);
    }

    public void listAllTasks() {
        JSONObject jsonObject = jsonUtil.getContent();
        if (jsonObject == null) {
            return;
        }

        JSONArray jsonTasks = jsonObject.getJSONArray("tasks");
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < jsonTasks.length(); i++) {
            JSONObject json = jsonTasks.getJSONObject(i);
            tasks.add(TaskConverter.jsonToTask(json));
        }

        tasks.forEach(System.out::println);

    }
}
