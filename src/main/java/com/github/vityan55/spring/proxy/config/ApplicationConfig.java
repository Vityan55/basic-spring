package com.github.vityan55.spring.proxy.config;

import com.github.vityan55.spring.proxy.bean.Customer;
import com.github.vityan55.spring.proxy.bean.IWaiter;
import com.github.vityan55.spring.proxy.bean.Waiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Currency;

@Configuration
@ComponentScan("com.github.vityan55.spring.proxy")
public class ApplicationConfig {

    @Bean
    public IWaiter john(){
        return new Waiter("John");
    }

    @Bean
    public Customer andrew(){
        return new Customer("Andrew");
    }

    @Bean
    public Customer nina(){
        return new Customer("Nina");
    }

    @Bean
    public Customer julia(){
        return new Customer("Julia");
    }
}
