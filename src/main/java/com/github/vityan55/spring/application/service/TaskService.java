package com.github.vityan55.spring.application.service;

import com.github.vityan55.spring.application.event.TaskCompletedEvent;
import com.github.vityan55.spring.application.model.Task;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final ApplicationEventPublisher publisher;

    private final List<Task> tasks = new ArrayList<>();

    public TaskService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void addTask(String description){
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setDescription(description);
        task.setCompleted(false);
        tasks.add(task);
    }

    public void completeTask(String taskID){
        var completedTask = tasks.stream()
                .filter(task -> task.getId().equals(taskID))
                .findFirst();
        completedTask.ifPresent(task -> task.setCompleted(true));
        publisher.publishEvent(new TaskCompletedEvent(
                this,
                taskID,
                completedTask.map(Task::isCompleted).orElse(false)
        ));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void saveTasks(String path){
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)){
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(tasks);
            oos.flush();
        } catch (IOException e){
            System.out.println("Exception trying to save file " + e.getMessage());
        }
    }

    public void loadTasks(String path){
        try (FileInputStream fileInputStream = new FileInputStream(path)){
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            tasks.addAll((List<Task>) ois.readObject());
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Exception trying to load from file " + e.getMessage());
        }
    }
}
