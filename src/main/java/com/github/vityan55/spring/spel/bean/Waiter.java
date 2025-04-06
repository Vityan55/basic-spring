package com.github.vityan55.spring.spel.bean;

import com.github.vityan55.spring.spel.annotation.Sentence;
import org.springframework.stereotype.Component;

@Component
public class Waiter {

    //значение выставляется через парсинг выражения в аннотации а также метод распечатки greeting в консоль
    @Sentence(expression = "setGreetingMessage(#bean)")
    private String greeting;

    public void printGreeting(){
        System.out.println("Waiter greet: " + greeting);
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
