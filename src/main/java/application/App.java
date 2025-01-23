package application;

import model.Operation;
import model.Status;
import service.TaskService;

import java.util.Arrays;

public class App {
    public void run(String[] args) {
        TaskService taskService = TaskService.getInstance();
        if (args.length < 1) {
            throw new IllegalArgumentException("You must provide an operation");
        }
        Operation op = Operation.getOperation(args[0].toUpperCase());
        switch (op) {
            case ADD -> {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Please add the description of the task");
                }
                taskService.add(args[1]);
            }
            case UPDATE -> {
                if (args.length != 3) {
                    throw new IllegalArgumentException("Please provide the id and updated description");
                }
                int id = Integer.parseInt(args[1]);
                taskService.update(id, args[2]);
            }
            case DELETE -> {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid arguments");
                }
                int id = Integer.parseInt(args[1]);
                taskService.delete(id);
            }
            case MARK_IN_PROGRESS -> {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid arguments");
                }
                int id = Integer.parseInt(args[1]);
                taskService.markStatus(id, Status.PROGRESS);
            }
            case MARK_DONE -> {
                if (args.length != 2) {
                    throw new IllegalArgumentException("Invalid arguments");
                }
                int id = Integer.parseInt(args[1]);
                taskService.markStatus(id, Status.DONE);
            }
            case LIST -> {
                if (args.length == 1) {
                    taskService.list();
                    break;
                }
                op = Operation.getOperation(args[1].toUpperCase());
                switch (op) {
                    case LIST_PROGRESS -> {
                        taskService.listByStatus(Status.PROGRESS);
                    }
                    case LIST_TODO -> {
                        taskService.listByStatus(Status.TODO);
                    }
                    case LIST_DONE -> {
                        taskService.listByStatus(Status.DONE);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + op);
                }

            }
            default -> {
                System.out.println("INVALID OPERATION");
            }
        }
    }
}
