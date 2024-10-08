import model.Status;
import service.TaskService;
import storage.JsonUtil;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        TaskService taskService = TaskService.getInstance();
        taskService.markTask(3, Status.PROGRESS);
        taskService.listAllTasks();
    }
}