package com.github.vityan55.spring.application;

import com.github.vityan55.spring.application.event.TasksSaveEvent;
import com.github.vityan55.spring.application.service.TaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        new Application().start();
    }

    public void start(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TaskService service = context.getBean(TaskService.class);
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. List Task");
            System.out.println("4. Save Task");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    System.out.println("Write a task description");
                    String description = scanner.nextLine();
                    service.addTask(description);
                    break;
                case 2:
                    System.out.println("Write a task ID");
                    String taskID = scanner.nextLine();
                    service.completeTask(taskID);
                    break;
                case 3:
                    service.getTasks().forEach(System.out::println);
                    break;
                case 4:
                    context.publishEvent(new TasksSaveEvent(
                            this
                    ));
                    break;
                case 5:
                    context.close();
                    return;
                default:
                    System.out.println("Invalid command. Try again");
            }
        }
    }
}
