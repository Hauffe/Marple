package com.pucpr.alexandre.marple.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "product_has_ingredients",
        primaryKeys = { "productId", "ingredientId" },
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns = "id",
                        childColumns = "productId"),
                @ForeignKey(entity = Ingredient.class,
                        parentColumns = "id",
                        childColumns = "ingredientId")
        })
public class ProductWithIngredients {
    @NonNull
    public final Long productId;
    @NonNull
    public final Long ingredientId;

    public ProductWithIngredients(Long productId, Long ingredientId) {
        this.productId = productId;
        this.ingredientId = ingredientId;
    }
}
