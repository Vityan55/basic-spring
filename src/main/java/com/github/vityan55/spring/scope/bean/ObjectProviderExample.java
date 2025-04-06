package com.github.vityan55.spring.scope.bean;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

//Интерфейс ObjectProvider. Он позволяет получать бины по типу
//Метод getObject() возвращает нам экземпляр бина, тип бина указан как Generic, также он может принимать параметры
// для создания экземпляра бина через конструктор.
@Component
public class ObjectProviderExample {
    private final ObjectProvider<Waiter> waiterObjectProvider;
    private final ObjectProvider<HeadChef> headChefObjectProvider;

    public ObjectProviderExample(ObjectProvider<Waiter> waiterObjectProvider, ObjectProvider<HeadChef> headChefObjectProvider) {
        this.waiterObjectProvider = waiterObjectProvider;
        this.headChefObjectProvider = headChefObjectProvider;
    }

    public void getAndCompareHeadChef(){
        HeadChef first = headChefObjectProvider.getObject();
        HeadChef second = headChefObjectProvider.getObject();

        System.out.println("Head chefs equals " + (first == second));
    }

    public void getAndCompareWaiters(){
        Waiter first = waiterObjectProvider.getObject(UUID.randomUUID());
        Waiter second = waiterObjectProvider.getObject(UUID.randomUUID());

        System.out.println("Waiters equals " + (first == second));
    }
}
