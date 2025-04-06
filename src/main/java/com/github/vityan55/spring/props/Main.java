package com.github.vityan55.spring.props;

import com.github.vityan55.spring.props.bean.Restaurant;
import com.github.vityan55.spring.props.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Restaurant restaurant = context.getBean(Restaurant.class);
        restaurant.printMainInfo();
        restaurant.printMenu();
    }
}
