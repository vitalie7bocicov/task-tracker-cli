package com.vitaliebocicov.task_tracker_cli;

import com.vitaliebocicov.task_tracker_cli.application.App;
import com.vitaliebocicov.task_tracker_cli.repository.TaskRepository;
import com.vitaliebocicov.task_tracker_cli.service.TaskService;

public class Main {
    public static void main(String[] args) {
        System.out.println("HELLO");
        TaskRepository repository = new TaskRepository();
        App app = new App(new TaskService(repository));
        try {
            app.run(args);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong. Please try again.");
        }
    }
}