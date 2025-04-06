package com.github.vityan55.spring.postProcessor.bean;

import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.List;

@Component
public class Restaurant {
    private LocalTime openTime;
    private final List<Food> menu;

    public Restaurant(List<Food> menu, LocalTime openTime) {
        this.menu = menu;
        this.openTime = openTime;
    }

    public void printInfo(){
        System.out.println("Open time is " + openTime);
        System.out.println("Restaurant menu " + menu);
    }
}
