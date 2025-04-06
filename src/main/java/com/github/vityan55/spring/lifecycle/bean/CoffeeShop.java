package com.github.vityan55.spring.lifecycle.bean;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

//по умолчанию синглтон
@Component
public class CoffeeShop {
    //Бин типа Prototype в бине типа Singleton всегда один и тот же поэтому надо использовать
    private final ObjectProvider<Coffee> coffeeObjectProvider;
    private final Map<String, Ingredient> ingredients;

    public CoffeeShop(ObjectProvider<Coffee> coffeeObjectProvider, Map<String, Ingredient> ingredients) {
        this.coffeeObjectProvider = coffeeObjectProvider;
        this.ingredients = ingredients;
    }

    //после создания бина
    @PostConstruct
    public void openShop(){
        System.out.println("Shop is open");
    }

    public void makeCoffee(){
        System.out.println("Making coffee");
    }

    public void makeCoffee(String type){
        Coffee coffee = coffeeObjectProvider.getObject(type);
        System.out.println("Making coffee " + coffee);
    }

    public void makeCoffee(String type, String ingredient){
        Coffee coffee = coffeeObjectProvider.getObject(type);
        System.out.println("Making coffee " + coffee + " with ingredient " + ingredients.get(ingredient));
    }

    //перед закрытием контекста
    @PreDestroy
    public void closeShop(){
        System.out.println("Shop is closed");
    }
}
