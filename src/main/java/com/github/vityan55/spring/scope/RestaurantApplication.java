package com.github.vityan55.spring.scope;

import com.github.vityan55.spring.scope.bean.HeadChef;
import com.github.vityan55.spring.scope.bean.ObjectProviderExample;
import com.github.vityan55.spring.scope.bean.OrderProcessor;
import com.github.vityan55.spring.scope.bean.Waiter;
import com.github.vityan55.spring.scope.config.RestaurantConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class RestaurantApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestaurantConfig.class);

        OrderProcessor orderProcessor = context.getBean(OrderProcessor.class);
        orderProcessor.processOrder("Coffee", 6);

        System.out.println("-----------------------------------------------------------------------");
        ObjectProviderExample objectProviderExample = context.getBean(ObjectProviderExample.class);

        objectProviderExample.getAndCompareHeadChef();
        objectProviderExample.getAndCompareWaiters();

        HeadChef first = context.getBean(HeadChef.class);
        HeadChef second = context.getBean(HeadChef.class);

        first.setName("Alex");

        System.out.println(first);
        System.out.println(second);

        Waiter firstWaiter = context.getBean(Waiter.class, UUID.randomUUID());
        Waiter secondWaiter = context.getBean(Waiter.class, UUID.randomUUID());

        firstWaiter.setTable(2);
        firstWaiter.setOrder("Salad");

        System.out.println(firstWaiter);
        System.out.println(secondWaiter);
    }
}
