package com.github.vityan55.spring.spel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class Shop {
    //с помощью метода получаем все продукты
    @Value("#{productList.getProducts()}")
    private List<ProductList.Product> products;

    //с помощью .? убеждаемся что getProducts() не возвращает null.
    //Если getProducts() не возвращает null то выражение продолжится.
    //Затем в квадратных скобках мы фильтруем наш список продуктов используя метод getFit()
    //В итоге останутся только те продукты для которых getFit() == true
    @Value("#{productList.getProducts().?[isFit()]}")
    private List<ProductList.Product> suitableProducts;

    //берем только те элементы коллекции количество которых меньше 10
    @Value("#{productList.getProducts().?[getCount() < 10]}")
    private List<ProductList.Product> endingProducts;

    //берем элементы коллекци по индесу
    @Value("#{productList.getProducts()[2]}")
    private List<ProductList.Product> vegetables;

    //буква Т означает оператор типа.
    //позволяет образаться к статическим методам, полям и константам класса, а также к методам конвертации типов
    @Value("#{T(java.time.LocalTime).of(9, 0)}")
    private LocalTime openTime;

    public void printAllProducts(){
        System.out.println("All products: " + products);
    }

    public void printSuitableProducts(){
        System.out.println("Suitable products: " + suitableProducts);
    }

    public void printEndingProducts(){
        System.out.println("Ending products: " + endingProducts);
    }

    public void printVegetables(){
        System.out.println("Vegetable: " + vegetables);
    }

    public void printOpenTime(){
        System.out.println("Open time: " + openTime);
    }

    public LocalTime getOpenTime() {
        return openTime;
    }
}
