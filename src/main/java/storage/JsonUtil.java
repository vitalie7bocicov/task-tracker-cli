package storage;

import model.Status;
import model.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtil {
    private final String PATH = "tasks.json";
    private final File jsonFile = new File(PATH);
    private Map<Integer, Task> tasks = new HashMap<>();

    public JsonUtil(){
        if (!jsonFile.exists()) {
            if (!initJsonFile()) {
                throw new RuntimeException("Failed to create json file");
            }
            System.out.println("File created successful");
            return;
        }
        // load tasks from file
        loadTasks();
    }

    public void saveTask(Task task) {
        task.setId(tasks.size());
        tasks.put(task.getId(), task);
        saveTasks();
    }

    private void saveTasks() {
        try (FileWriter fw = new FileWriter(jsonFile)) {
            int countTask = 0;
            fw.write("[\n");
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                try {
                    ++countTask;
                    fw.write(getJsonFromObject(entry.getValue()));
                    if (countTask == tasks.size()) {
                        continue;
                    }
                    fw.write(",\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fw.write("\n]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTasks() {
        List<String> taskStrings = extractTaskStringsFromFile();
        for (String taskObj : taskStrings) {
            System.out.println(taskObj);
            Task task = new Task();
            Pattern pattern = Pattern.compile("\"(.*?)\"\\s*:\\s*\"(.*?)\",?");
            Matcher matcher = pattern.matcher(taskObj);
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);
                switch (key) {
                    case "id" -> {
                        task.setId(Integer.parseInt(value));
                    }
                    case "description" -> {
                        task.setDescription(value);
                    }
                    case "status" -> {
                        task.setStatus(Status.valueOf(value));
                    }
                    case "createdAt" -> {
                        if (value.equals("null")) {
                            task.setCreatedAt(null);
                        } else {
                            task.setCreatedAt(LocalDateTime.parse(value));
                        }
                    }
                    case "updatedAt" -> {
                        if (value.equals("null")) {
                            task.setUpdatedAt(null);
                        } else {
                            task.setUpdatedAt(LocalDateTime.parse(value));
                        }
                    }
                }
                tasks.put(task.getId(), task);
            }
        }
    }

    private List<String> extractTaskStringsFromFile() {
        boolean insideTaskObject = false;
        StringBuilder sb = new StringBuilder();
        List<String> taskStrings = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(jsonFile.toPath())) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("[")) {
                    continue;
                }
                // task found
                if (line.startsWith("{")) {
                    insideTaskObject = true;
                    sb.append(line.trim());
                    continue;
                }
                // task object ended
                if (line.startsWith("}")) {
                    insideTaskObject = false;
                    taskStrings.add(sb.toString());
                    sb.setLength(0); // reset the string builder
                    continue;
                }
                if (insideTaskObject) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return taskStrings;
    }

    private boolean initJsonFile() {
        boolean initStatus;
        try {
            initStatus = jsonFile.createNewFile();
            if (!initStatus) {
                return false;
            }
            FileWriter fw = new FileWriter(jsonFile);
            fw.write("[]\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return initStatus;
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

