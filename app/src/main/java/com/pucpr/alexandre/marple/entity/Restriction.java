package com.pucpr.alexandre.marple.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "restriction")
public class Restriction {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    private List<Ingredient> ingredients;

    public Restriction(Long id, String name) {
        this.id = id;
        this.name = name;
        ingredients = new ArrayList<>();
    }

    @Ignore
    public Restriction() {
    }

    @Ignore
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
