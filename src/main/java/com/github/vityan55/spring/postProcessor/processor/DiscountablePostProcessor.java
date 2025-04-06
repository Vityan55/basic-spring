package com.github.vityan55.spring.postProcessor.processor;

import com.github.vityan55.spring.postProcessor.annotation.Discount;
import com.github.vityan55.spring.postProcessor.bean.Food;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class DiscountablePostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //проверяем что бин типа Food
        if (bean instanceof Food food){
            //получаем класс бина
            Class<Food> foodClass = (Class<Food>) bean.getClass();
            try {
                //получаем поле discount
                Field discountField = foodClass.getDeclaredField("discount");
                //проыеряем аннотированно ли это поле и является ли тип еды пастой
                if (discountField.isAnnotationPresent(Discount.class) && food.getType().equals("Pasta")){
                    //получаем аннотацию над полем discount
                    Discount discount = discountField.getAnnotation(Discount.class);
                    //присваиваем скидку на пасту, забирая процент из аннотации
                    food.setDiscount(discount.percent());
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }
}
