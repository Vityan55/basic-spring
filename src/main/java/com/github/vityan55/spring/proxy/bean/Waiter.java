package com.github.vityan55.spring.proxy.bean;

import java.time.Duration;

public class Waiter implements IWaiter {

    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    @Override
    public void serve(String customerName) {
        System.out.println(name + " is serving customer " + customerName);

        try {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println(name + " is free");
    }
}
