package com.github.vityan55.spring.di.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    // 3 способ внедрения зависимостей (не рекомендуется из-за осложнения маркирования бинов при написании тестов,
    // неявности зависимости класса от другого компонента при чтении кода, а также некоторых ограничений при
    // взаимодействии с жизненным циклом бинов)
    //@Autowired
    private Waiter waiter;

    public void makeOrder(String order){
        System.out.println("Customer making order " + order);
        waiter.takeOrder(order);
    }

    //2 вариант внедрения зависимостей
    @Autowired
    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
