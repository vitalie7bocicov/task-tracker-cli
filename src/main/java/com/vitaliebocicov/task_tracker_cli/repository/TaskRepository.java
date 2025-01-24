package com.vitaliebocicov.task_tracker_cli.repository;

import com.vitaliebocicov.task_tracker_cli.model.Task;
import com.vitaliebocicov.task_tracker_cli.util.JsonUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskRepository {

    private final JsonUtil jsonUtil;
    private final Map<Integer, Task> tasks;
    private int maxId;

    public TaskRepository() {
        this.jsonUtil = new JsonUtil();
        this.tasks = jsonUtil.loadTasks();
        this.maxId = Collections.max(tasks.keySet());
    }

    public Task save(Task task) {
        if (!tasks.containsKey(task.getId())) {
            System.out.println();
            task.setId(++maxId);
            tasks.put(task.getId(), task);
        }
        tasks.put(task.getId(), task);
        jsonUtil.saveTasks(tasks.values().stream().toList());
        return task;
    }

    public List<Task> getTasks() {
        return tasks.values().stream().toList();
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public void update(Task task) {
        Task taskToUpdate = tasks.get(task.getId());
        if (taskToUpdate == null) {
            throw new RuntimeException("Task with this id does not exist");
        }
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setUpdatedAt(LocalDateTime.now());
        jsonUtil.saveTasks(tasks.values().stream().toList());
    }

    public void deleteById(int id) {
        tasks.remove(id);
        jsonUtil.saveTasks(tasks.values().stream().toList());
    }
}
