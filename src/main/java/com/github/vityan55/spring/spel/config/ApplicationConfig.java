package com.github.vityan55.spring.spel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.github.vityan55.spring.spel")
@PropertySource("classpath:application-spel.properties")
public class ApplicationConfig {
}
