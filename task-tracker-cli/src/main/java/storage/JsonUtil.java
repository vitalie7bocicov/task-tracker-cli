package storage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {
    private static JsonUtil jsonUtil;
    private final String PATH = "tasks.json";
    private final File jsonFile = new File(PATH);

    private JsonUtil(){
        if (!jsonFile.exists()) {
            if (!initJsonFile()) {
                System.out.println("Failed to init json file");
                System.exit(0);
            }
            JSONObject jsonObject = new JSONObject();
            JSONArray tasks = new JSONArray();
            jsonObject.put("tasks", tasks);
            writeJsonObject(jsonObject);
        }
    }

    private boolean initJsonFile() {
        try {
            if (jsonFile.createNewFile()) {
                System.out.println("json file created.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the json file");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static JsonUtil getInstance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }

    public JSONObject getContent() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(PATH)));
            return new JSONObject(content);
        } catch (IOException e) {
            System.out.println("Failed to read json file content");
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getTasksArray() {
        JSONObject jsonObject = getContent();
        if (jsonObject == null) {
            throw new RuntimeException("Failed to get tasks array");
        }

       return jsonObject.getJSONArray("tasks");
    }

    public void writeJsonObject(JSONObject jsonObject) {
        try (FileWriter fileWriter = new FileWriter(PATH)) {
            fileWriter.write(jsonObject.toString(4));
        } catch (IOException e) {
            System.out.println("Failed to write json object to file");
            e.printStackTrace();
        }
    }
}
