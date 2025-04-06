package com.github.vityan55.spring.scope.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//Scope Singleton - единственный экземпляр доступный глобально
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HeadChef {
    private String name;

    public void makeOrder(Waiter waiter){
        System.out.println("Head chef is making order from " + waiter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeadChef{" +
                "name='" + name + '\'' +
                '}';
    }
}
