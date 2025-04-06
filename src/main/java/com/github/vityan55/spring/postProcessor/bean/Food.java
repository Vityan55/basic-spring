package com.github.vityan55.spring.postProcessor.bean;

import com.github.vityan55.spring.postProcessor.annotation.Discount;

public class Food {
    private String type;
    private String dishName;

    @Discount(percent = 30)
    private int discount;

    public Food(String type, String dishName) {
        this.type = type;
        this.dishName = dishName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "type='" + type + '\'' +
                ", dishName='" + dishName + '\'' +
                ", discount=" + discount +
                '}';
    }
}
