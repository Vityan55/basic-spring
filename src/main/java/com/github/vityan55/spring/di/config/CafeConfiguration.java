package com.github.vityan55.spring.di.config;

import com.github.vityan55.spring.di.bean.Kitchen;
import com.github.vityan55.spring.di.bean.Waiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.vityan55.spring.di")
public class CafeConfiguration {
    //4 вариант внедрения зависимостей
    @Bean
    public Waiter waiter(Kitchen kitchen){
        return new Waiter(kitchen);
    }
}
