package storage;

import model.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonUtil {
    private final String PATH = "tasks.json";
    private final File jsonFile = new File(PATH);

    public JsonUtil(){
        if (!jsonFile.exists()) {
            if (!initJsonFile()) {
                throw new RuntimeException("Failed to create json file");
            }
            System.out.println("File created successful");
        }
    }

    
    public void saveTask(Task task) {
        String jsonTask = getJsonFromObject(task);
        try (FileWriter fw = new FileWriter(jsonFile)) {
            fw.write(jsonTask);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(jsonFile.toPath())) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return tasks;
    }

    private boolean initJsonFile() {
        boolean isFileCreated;
        try {
            isFileCreated = jsonFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return isFileCreated;
    }

    private String getJsonFromObject(Task task) {
        return "{\n" +
                "  \"id\": \"" + task.getId() + "\", \n" +
                "  \"description\": \"" + task.getDescription() + "\", \n" +
                "  \"status\": \"" + task.getStatus() + "\", \n" +
                "  \"createdAt\": \"" + task.getCreatedAt() + "\", \n" +
                "  \"updatedAt\": \"" + task.getUpdatedAt() + "\" \n" +
                "}";
    }
}
