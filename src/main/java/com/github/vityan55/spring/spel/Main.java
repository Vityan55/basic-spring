package com.github.vityan55.spring.spel;

import com.github.vityan55.spring.spel.bean.*;
import com.github.vityan55.spring.spel.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalTime;
import java.util.Locale;

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

        BeanExpressionParser parser = context.getBean(BeanExpressionParser.class);
        System.out.println("Evaluate open time: " + parser.evaluate("shop.getOpenTime()", LocalTime.class));
        System.out.println("Evaluate customer greeting " + parser.evaluate("customer.getGreeting()", String.class));
        System.out.println("Evaluate waiter greeting " + parser.evaluate("waiter.getGreeting()", String.class));
    }
}
