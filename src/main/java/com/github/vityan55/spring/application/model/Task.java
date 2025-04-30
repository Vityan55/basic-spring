package com.github.vityan55.spring.application.model;

import java.io.Serial;
import java.io.Serializable;

public class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String description;

    private boolean completed = false;

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ": " + description + " (Completed: " + completed + ')';
    }
}
