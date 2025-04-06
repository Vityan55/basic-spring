package com.github.vityan55.spring.lifecycle;

import com.github.vityan55.spring.lifecycle.bean.CoffeeShop;
import com.github.vityan55.spring.lifecycle.config.LifecycleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfiguration.class);
        CoffeeShop shop = context.getBean(CoffeeShop.class);

//        shop.makeCoffee();
//        shop.makeCoffee("Latte");
        shop.makeCoffee("Cappuccino", "sugar");

        context.close();
    }
}
