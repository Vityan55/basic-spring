package com.github.vityan55.spring.spel.bean;

import com.github.vityan55.spring.spel.annotation.Sentence;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    //2 вариант выражения, тк мы установили root в context
    //@Sentence(expression = "#root.setStaticGreetingMessage(#bean)")
    //само выражение - это вызов метода с переменной под именем bean в пакете processor
    @Sentence(expression = "setStaticGreetingMessage(#bean)")
    private String greeting;

    public void printGreeting(){
        System.out.println("Customer greeting: " + greeting);
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
