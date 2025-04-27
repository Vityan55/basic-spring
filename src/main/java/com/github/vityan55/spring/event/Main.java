package com.github.vityan55.spring.event;

import com.github.vityan55.spring.event.bean.Restaurant;
import com.github.vityan55.spring.event.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // context.start();
        // context.stop();

        // Invoke-WebRequest -Uri http://localhost:8080/order -Method POST -Body "Coffee"

        Restaurant restaurant = context.getBean(Restaurant.class);
        restaurant.placeOrder("Salad");
        restaurant.placeOrder("Pasta");
    }
}
