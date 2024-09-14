package util;

import model.Status;
import org.json.JSONObject;
import model.Task;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public static Task jsonToTask(JSONObject json) {
        return new Task(
                json.getInt("id"),
                json.getString("description"),
                Status.valueof(json.getString("status")),
                LocalDateTime.parse(json.getString("createdAt")),
                json.getString("updatedAt").equals("null") ? null : LocalDateTime.parse(json.getString("updatedAt"))
        );
    }
}
