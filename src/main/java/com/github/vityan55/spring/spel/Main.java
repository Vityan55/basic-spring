package com.github.vityan55.spring.spel;

import com.github.vityan55.spring.spel.bean.Customer;
import com.github.vityan55.spring.spel.bean.Shop;
import com.github.vityan55.spring.spel.bean.SimpleSpelExamples;
import com.github.vityan55.spring.spel.bean.Waiter;
import com.github.vityan55.spring.spel.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        SimpleSpelExamples simpleSpelExamples = context.getBean(SimpleSpelExamples.class);
        simpleSpelExamples.simpleEval();
        System.out.println("----------------------------------------");

        Shop shop = context.getBean(Shop.class);
        shop.printAllProducts();
        System.out.println("----------------------------------------");
        shop.printSuitableProducts();
        System.out.println("----------------------------------------");
        shop.printEndingProducts();
        System.out.println("----------------------------------------");
        shop.printVegetables();
        System.out.println("----------------------------------------");
        shop.printOpenTime();
        System.out.println("----------------------------------------");

        simpleSpelExamples.simpleEvaluateContextExample();

        Waiter waiter = context.getBean(Waiter.class);
        Customer customer = context.getBean(Customer.class);

        waiter.printGreeting();
        customer.printGreeting();
        System.out.println("---------------------------------------");
    }
}
