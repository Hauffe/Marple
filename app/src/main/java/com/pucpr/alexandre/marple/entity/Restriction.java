package com.pucpr.alexandre.marple.entity;

import java.util.ArrayList;
import java.util.List;

public class Restriction {
    private Long id;
    private String name;
    private List<Ingredient> ingredients;

    public Restriction(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
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
