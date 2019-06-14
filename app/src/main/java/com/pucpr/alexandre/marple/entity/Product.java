package com.pucpr.alexandre.marple.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    private List<Ingredient> ingredients;


    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
        ingredients = new ArrayList<>();
    }

    @Ignore
    public Product(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
    }

    @Ignore
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
