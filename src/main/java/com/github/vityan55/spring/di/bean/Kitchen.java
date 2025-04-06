package com.github.vityan55.spring.di.bean;

import org.springframework.stereotype.Component;

@Component
public class Kitchen {
    public void cookOrder(String order){
        System.out.println("Kitchen is cooking order " + order);
    }
}
