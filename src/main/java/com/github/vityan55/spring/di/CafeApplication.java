package com.github.vityan55.spring.di;

import com.github.vityan55.spring.di.bean.Customer;
import com.github.vityan55.spring.di.config.CafeConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CafeApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CafeConfiguration.class);

        Customer customer = context.getBean("customer", Customer.class);
        customer.makeOrder("Salad");
    }
}
