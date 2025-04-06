package com.github.vityan55.spring.lifecycle.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//прототип - создается много раз
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Coffee {
    private String type;

    public Coffee(String type) {
        this.type = type;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct -> make coffee with type " + type);
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy -> before destroy");
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type='" + type + '\'' +
                '}';
    }
}
