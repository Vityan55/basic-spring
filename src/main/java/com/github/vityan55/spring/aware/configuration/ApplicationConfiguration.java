package com.github.vityan55.spring.aware.configuration;

import com.github.vityan55.spring.aware.bean.Chief;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.vityan55.spring.aware")
public class ApplicationConfiguration {
    //еще вариант внедрения бина
    @Bean
    public Chief restaurantChief(){
        return new Chief();
    }
}
