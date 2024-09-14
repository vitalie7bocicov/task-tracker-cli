package service;

import model.Status;
import org.json.JSONArray;
import org.json.JSONObject;
import storage.JsonUtil;
import model.Task;
import util.TaskConverter;

import java.time.LocalDateTime;
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

    public void updateTask(int id, String description) {
        JSONObject jsonObject = jsonUtil.getContent();
        if (jsonObject == null) {
            return;
        }

        JSONArray tasks = jsonObject.getJSONArray("tasks");

        for (int i = 0; i < tasks.length(); i++) {
            JSONObject task = tasks.getJSONObject(i);
            if (task.getInt("id") == id) {
                task.put("description", description);
                task.put("updatedAt", LocalDateTime.now());
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

    public void markTask(int id, Status status) {
        JSONObject jsonObject = jsonUtil.getContent();
        if (jsonObject == null) {
            return;
        }

        JSONArray tasks = jsonObject.getJSONArray("tasks");

        for (int i = 0; i < tasks.length(); i++) {
            JSONObject task = tasks.getJSONObject(i);
            if (task.getInt("id") == id) {
                task.put("status", status.toString());
                task.put("updatedAt", LocalDateTime.now());
                break;
            }
        }

        jsonObject.put("tasks", tasks);
        jsonUtil.writeJsonObject(jsonObject);
    }
}
