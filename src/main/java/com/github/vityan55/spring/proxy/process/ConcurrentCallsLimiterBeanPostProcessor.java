package com.github.vityan55.spring.proxy.process;

import com.github.vityan55.spring.proxy.bean.IWaiter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Semaphore;

@Component
public class ConcurrentCallsLimiterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (bean instanceof IWaiter){
            // реализуем прокси с помощью JDK Dynamic Proxy
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                // обрабатывает каждый метод бина. Если нужно, такую обработку можно написать для конкретного метода
                // бина. Вся необходимая информация находится в моле method, а в args хранятся параметры которые
                // передаются при вызове конкретного метода

                private final Semaphore semaphore = new Semaphore(1);

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    try {
                        System.out.println("Acquire... " + semaphore.availablePermits());
                        semaphore.acquire();
                        // вызывает главный метод в бине Waiter
                        return method.invoke(bean, args);
                    } finally {
                        semaphore.release();
                        System.out.println("Release... ");
                    }
                }
            });
        }

        return bean;
    }
}
