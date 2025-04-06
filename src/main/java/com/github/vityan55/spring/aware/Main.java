package com.github.vityan55.spring.aware;

import com.github.vityan55.spring.aware.bean.Menu;
import com.github.vityan55.spring.aware.bean.Waiter;
import com.github.vityan55.spring.aware.configuration.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Waiter waiter = context.getBean(Waiter.class);
        waiter.takeOrder("Fish");

        waiter.greetCustomer();

        context.getBean(Menu.class).printMenu();
    }
}
