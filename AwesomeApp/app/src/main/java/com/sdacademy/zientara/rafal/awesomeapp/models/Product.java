package com.sdacademy.zientara.rafal.awesomeapp.models;

import com.activeandroid.Model;

/**
 * Created by Evil on 28.07.2017.
 */

public class Product extends Model {

    private String name;

    private Category category;

    private Double count;

    private Double price;

    private Boolean isPurchased;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", count=" + count +
                ", price=" + price +
                ", isPurchased=" + isPurchased +
                '}';
    }
}
