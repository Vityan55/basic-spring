package com.github.vityan55.spring.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@ComponentScan("com.github.vityan55.spring.event")
public class ApplicationConfig {

    @Bean
    public BlockingQueue<String> orderQueue(){
        return new LinkedBlockingQueue<>();
    }

    // Отвечает за многопоточный мультикастинг событий в приложении
    // Для того чтобы обработка событий выполнялась в фоновом режиме, не блокируя основной поток выполнения приложения
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(){
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

        // добавляем экзекьютор
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        // добавили декоратор который выводит сообщение перед отправкой наших событий
        executor.setTaskDecorator(runnable -> {
            System.out.println("Call task from executor");
            return runnable;
        });

        eventMulticaster.setTaskExecutor(executor);
        return eventMulticaster;
    }
}
