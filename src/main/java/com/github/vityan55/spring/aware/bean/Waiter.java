package com.github.vityan55.spring.aware.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Waiter implements ApplicationContextAware, BeanFactoryAware, EnvironmentAware {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private Environment environment;

    //позволяет бину получить доступ к контексту приложения спринг в котором он создан
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void takeOrder(String dish){
        System.out.println("Taking " + dish);
        beanFactory.getBean(Chief.class).cook(dish);
    }

    public void greetCustomer(){
        String daytime = environment.getProperty("daytime");
        System.out.println("Good " + daytime);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    //Сначала создаем переменную среды (Edit Configuration -> Environment Variable (нажать на доллар)
    // -> нажать на плюсик, ввести тип
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
