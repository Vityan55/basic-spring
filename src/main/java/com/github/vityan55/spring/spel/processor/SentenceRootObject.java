package com.github.vityan55.spring.spel.processor;

import com.github.vityan55.spring.spel.bean.Customer;
import com.github.vityan55.spring.spel.bean.Waiter;

import java.util.HashMap;
import java.util.Map;

//класс из которого мы будем брать приветствия в StandardEvaluationContext
public class SentenceRootObject {
    private static final Map<Class<?>, String> GREETING_MESSAGES = new HashMap<>();

    static {
        GREETING_MESSAGES.put(Waiter.class, "Hello! I'm waiter today!");
        GREETING_MESSAGES.put(Customer.class, "Hello! Can I place order?");
    }



    //будем использовать его при настройке SpEl контекста
    public static void setMessageFor(Object bean){
        if (!GREETING_MESSAGES.containsKey(bean.getClass()))
            return;

        if (bean.getClass().equals(Waiter.class)){
            ((Waiter) bean).setGreeting(GREETING_MESSAGES.get(bean.getClass()));
        } else ((Customer) bean).setGreeting(GREETING_MESSAGES.get(bean.getClass()));
    }
}
