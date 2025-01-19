import model.Operation;
import service.TaskService;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = TaskService.getInstance();
        System.out.println(Arrays.toString(args));
        if (args.length < 1) {
            throw new IllegalArgumentException("You must provide an operation");
        }
        Operation op = Operation.valueOf(args[0].toUpperCase());
        switch (op) {
            case ADD -> {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Please add the description of the task");
                }
                taskService.add(args[1]);
            }
            default -> {
                System.out.println("INVALID OPERATION");
            }
        }
        taskService.list();
    }
}