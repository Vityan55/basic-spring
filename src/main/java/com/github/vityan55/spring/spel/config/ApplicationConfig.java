package com.github.vityan55.spring.spel.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

//настройка SpEl контекста для работы с бинами
@Configuration
@ComponentScan("com.github.vityan55.spring.spel")
@PropertySource("classpath:application-spel.properties")
public class ApplicationConfig {

    @Bean
    public SpelExpressionParser spelExpressionParser(){
        return new SpelExpressionParser();
    }

    //рутовый контекст который позволяет работать с бинами в наших выражениях
    //первый параметр это BeanFactory из которого будут доставаться бины
    //второй параметр - Scope к которому тоже могут искаться бины если они не были найдены в BeanFactory
    //Мы выставили его null тк все бины в наших SpEl выражениях будут находиться в BeanFactory
    @Bean
    public BeanExpressionContext beanExpressionContext(BeanFactory beanFactory){
        return new BeanExpressionContext((ConfigurableBeanFactory) beanFactory, null);
    }

    //создаем сам SpEl context
    @Bean
    public StandardEvaluationContext standardEvaluationContext(
            BeanFactory beanFactory,
            BeanExpressionContext beanExpressionContext
    ){
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

        //выставляем резолвер и аксессор которые играют роль посредника между SpEl и бинами
        evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        evaluationContext.addPropertyAccessor(new BeanExpressionContextAccessor());

        //выставялем наш рутовый объект
        evaluationContext.setRootObject(beanExpressionContext);

        return evaluationContext;
    }
}
