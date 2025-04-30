package com.github.vityan55.spring.application.event;

import org.springframework.context.ApplicationEvent;

public class TasksSaveEvent extends ApplicationEvent {

    public TasksSaveEvent(Object source) {
        super(source);
    }
}
