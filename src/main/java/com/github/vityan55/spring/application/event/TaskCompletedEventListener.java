package com.github.vityan55.spring.application.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskCompletedEventListener {
    @EventListener(TaskCompletedEvent.class)
    public void onEvent(TaskCompletedEvent event){
        if (event.isCompleted()){
            System.out.println("Event is completed. ID " + event.getTaskId());
        } else {
            System.out.println("Event is not completed, ID " + event.getTaskId());
        }
    }
}
