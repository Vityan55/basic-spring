package com.github.vityan55.spring.profile;

import com.github.vityan55.spring.profile.bean.CoffeeShop;
import com.github.vityan55.spring.profile.bean.Holder;
import com.github.vityan55.spring.profile.bean.LazyBean;
import com.github.vityan55.spring.profile.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        CoffeeShop coffeeShop = context.getBean(CoffeeShop.class);
        coffeeShop.orderCoffee();
        coffeeShop.printShopName();

        Holder holder = context.getBean(Holder.class);
        holder.fireLazyBean();

        LazyBean lazyBean = context.getBean(LazyBean.class);
        lazyBean.fireHolderBean();
    }
}
