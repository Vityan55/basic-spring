package com.github.vityan55.spring.di.bean;

 public class Waiter {
    private final Kitchen kitchen;

    //1 способ внедрения зависимостей
    public Waiter(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void takeOrder(String order){
        System.out.println("Waiter getting order " + order);
        kitchen.cookOrder(order);
    }
}
