package com.github.vityan55.spring.props.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan("com.github.vityan55.spring.props")
//Настройка Spring на чтение свойств приложения
//Указываем путь к файлу properties, Чтобы спринг мог его найти
//Указываем файл с нашими свойствами и спринг внедряет их в наше приложение
//1 способ
//@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    //2 сопосб (.properties)
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties(){
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        configurer.setLocation(new ClassPathResource("application.properties"));
//        return configurer;
//    }
    //для yml/yaml
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
