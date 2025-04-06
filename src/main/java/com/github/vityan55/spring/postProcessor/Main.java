package com.github.vityan55.spring.postProcessor;

import com.github.vityan55.spring.postProcessor.bean.Restaurant;
import com.github.vityan55.spring.postProcessor.bean.Waiter;
import com.github.vityan55.spring.postProcessor.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Waiter waiter = context.getBean(Waiter.class);
        waiter.takeOrder();

        Restaurant restaurant = context.getBean(Restaurant.class);
        restaurant.printInfo();
    }
}
