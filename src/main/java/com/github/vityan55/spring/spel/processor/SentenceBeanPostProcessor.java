package com.github.vityan55.spring.spel.processor;

import com.github.vityan55.spring.spel.annotation.Sentence;
import com.github.vityan55.spring.spel.bean.Customer;
import com.github.vityan55.spring.spel.bean.Waiter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class SentenceBeanPostProcessor implements BeanPostProcessor {

    private static final String SET_GREETING_MESSAGE_METHOD = "setGreetingMessage";

    private static final String SET_STATIC_GREETING_MESSAGE_METHOD = "setStaticGreetingMessage";

    private static final String GREETING_FIELD = "greeting";

    private static final String BEAN_VARIABLE = "bean";

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private final StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

    private final SentenceRootObject rootObject = new SentenceRootObject();

    //конфигурация StandardEvaluationContext
    @PostConstruct
    public void configureContext(){
        //присваиваем rootObject
        evaluationContext.setRootObject(rootObject);
        evaluationContext.registerFunction(SET_GREETING_MESSAGE_METHOD,
                getMethod(rootObject, SET_GREETING_MESSAGE_METHOD, Object.class));
        evaluationContext.registerFunction(SET_GREETING_MESSAGE_METHOD,
                getMethod(SentenceRootObject.class, SET_STATIC_GREETING_MESSAGE_METHOD, Object.class));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == Waiter.class || bean.getClass() == Customer.class){
            //выставляем для контекста переменную с именем bean
            evaluationContext.setVariable(BEAN_VARIABLE, bean);

            try {
                //получаем поле greeting
                Field field = bean.getClass().getDeclaredField(GREETING_FIELD);
                //получаем аннотацию над эти полем
                Sentence sentence = field.getAnnotation(Sentence.class);
                //получаем значение поля expression у аннотации
                parser.parseExpression(sentence.expression()).getValue(evaluationContext);
            } catch (NoSuchFieldException e){
                throw new RuntimeException(e);
            }
        }

        return bean;
    }

    //реализовали функцию для получения метода через объект
    private Method getMethod(Object object, String name, Class<?> ... args) {
        try {
            return object.getClass().getDeclaredMethod(name, args);
        } catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }

    //реализовали функцию для получения метода через класс
    private Method getMethod(Class<?> type, String name, Class<?> ... args) {
        try {
            return type.getDeclaredMethod(name, args);
        } catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }
}
