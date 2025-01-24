package com.vitaliebocicov.task_tracker_cli.application;

import com.vitaliebocicov.task_tracker_cli.model.Operation;
import com.vitaliebocicov.task_tracker_cli.model.Status;
import com.vitaliebocicov.task_tracker_cli.service.TaskService;

public class App {

    private final TaskService taskService;

    public App(TaskService taskService) {
        this.taskService = taskService;
    }

    public void run(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("You must provide an operation");
        }
        Operation op = Operation.getOperation(args[0].toUpperCase());
        switch (op) {
            case ADD -> {
                taskService.add(args[1]);
            }
            case UPDATE -> {
                int id = Integer.parseInt(args[1]);
                taskService.updateDescription(id, args[2]);
            }
            case DELETE -> {
                int id = Integer.parseInt(args[1]);
                taskService.delete(id);
            }
            case MARK_IN_PROGRESS -> {
                int id = Integer.parseInt(args[1]);
                taskService.markStatus(id, Status.PROGRESS);
            }
            case MARK_DONE -> {
                int id = Integer.parseInt(args[1]);
                taskService.markStatus(id, Status.DONE);
            }
            case LIST -> {
                if (args.length == 1) {
                    taskService.list();
                    break;
                }
                handleListByStatus(args[1]);
            }
            default -> {
                System.out.println("INVALID OPERATION");
            }
        }
    }

    private void handleListByStatus(String cmd) {
        Operation op = Operation.getOperation(cmd.toUpperCase());
        switch (op) {
            case LIST_TODO -> {
                taskService.listByStatus(Status.TODO);
            }
            case LIST_PROGRESS -> {
                taskService.listByStatus(Status.PROGRESS);
            }
            case LIST_DONE -> {
                taskService.listByStatus(Status.DONE);
            }
            default -> throw new IllegalStateException("Unexpected value: " + op);
        }
    }
}
