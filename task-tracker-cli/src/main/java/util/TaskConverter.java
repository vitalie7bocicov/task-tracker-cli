package util;

import org.json.JSONObject;
import model.Task;

public class TaskConverter {
    public static JSONObject taskToJson(Task task) {
        JSONObject json = new JSONObject();
        json.put("id", task.getId());
        json.put("description", task.getDescription());
        json.put("status", task.getStatus().toString());
        json.put("createdAt", task.getCreatedAt().toString());
        json.put("updatedAt", task.getUpdatedAt() == null? "null" :task.getUpdatedAt().toString());
        return json;
    }
}
