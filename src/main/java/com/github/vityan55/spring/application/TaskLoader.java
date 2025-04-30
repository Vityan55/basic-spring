package com.github.vityan55.spring.application;

import com.github.vityan55.spring.application.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("load")
public class TaskLoader {
    @Value("${app.tasks-file-path}")
    private String filePath;

    private final TaskService taskService;

    public TaskLoader(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostConstruct
    public void loadTasks(){
        System.out.println("Load tasks...");
        taskService.loadTasks(filePath);
    }
}
