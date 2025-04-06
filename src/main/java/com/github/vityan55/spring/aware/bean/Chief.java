package com.github.vityan55.spring.aware.bean;

import org.springframework.beans.factory.BeanNameAware;

public class Chief implements BeanNameAware {

    private String name;

    public void cook(String dish){
        System.out.println("Cooking " + dish + " Name is " + name);
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
