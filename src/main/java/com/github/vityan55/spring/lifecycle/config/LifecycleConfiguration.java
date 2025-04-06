package com.github.vityan55.spring.lifecycle.config;

import com.github.vityan55.spring.lifecycle.bean.IngredientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.vityan55.spring.lifecycle")
public class LifecycleConfiguration {

    @Bean
    public IngredientFactoryBean sugar(){
        return new IngredientFactoryBean("sugar");
    }

    @Bean
    public IngredientFactoryBean milk(){
        return new IngredientFactoryBean("milk");
    }
}
