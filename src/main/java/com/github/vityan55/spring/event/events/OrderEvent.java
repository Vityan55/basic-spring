package com.github.vityan55.spring.event.events;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {

    private final String orderDetails;

    public OrderEvent(Object source, String orderDetails) {
        super(source);
        this.orderDetails = orderDetails;
    }

    public String getOrderDetails() {
        return orderDetails;
    }
}
