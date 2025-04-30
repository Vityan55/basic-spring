package com.github.vityan55.spring.application.event;

import com.github.vityan55.spring.application.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TasksSaveEventListener {

    @Value("${app.tasks-file-path}")
    private String filePath;

    private final TaskService taskService;

    public TasksSaveEventListener(TaskService taskService) {
        this.taskService = taskService;
    }

    @EventListener(TasksSaveEvent.class)
    public void onEvent(TasksSaveEvent event){
        System.out.println("Save tasks...");
        taskService.saveTasks(filePath);
    }
}
