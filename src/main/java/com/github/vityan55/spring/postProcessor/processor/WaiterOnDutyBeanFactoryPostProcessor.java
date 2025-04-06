package com.github.vityan55.spring.postProcessor.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class WaiterOnDutyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //получили BeanDefinition с именем "waiter"
        BeanDefinition definition = beanFactory.getBeanDefinition("waiter");
        //получаем поля бина "waiter"
        MutablePropertyValues values = definition.getPropertyValues();
        //изменяем свойство onDuty
        values.add("onDuty", true);
    }
}
