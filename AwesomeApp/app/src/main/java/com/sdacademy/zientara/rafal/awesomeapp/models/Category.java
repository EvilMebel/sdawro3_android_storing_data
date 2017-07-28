package com.sdacademy.zientara.rafal.awesomeapp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Evil on 27.07.2017.
 */

//@Table(name = "Categories")
public class Category extends Model {
    public static final String COLUMN_NAME = "cname";

    @Column(name = COLUMN_NAME, notNull = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}