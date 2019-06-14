package com.pucpr.alexandre.marple.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.entity.Product;
import com.pucpr.alexandre.marple.entity.ProductWithIngredients;

import java.util.List;

@Dao
public interface ProductWithIngredientsDAO {
    @Insert
    void insert(ProductWithIngredients productWithIngredients);

    @Delete
    void delete(ProductWithIngredients productWithIngredients);

    @Query("DELETE FROM restriction_has_ingredients")
    int clean();

    @Query("SELECT * FROM product " +
            "INNER JOIN product_has_ingredients " +
            "ON product.id=product_has_ingredients.productId " +
            "WHERE product_has_ingredients.ingredientId=:ingredientId")
    List<Product> getProductsForIngredient(final Long ingredientId);

    @Query("SELECT * FROM ingredient " +
            "INNER JOIN product_has_ingredients " +
            "ON ingredient.id=product_has_ingredients.ingredientId " +
            "WHERE product_has_ingredients.productId=:productId")
    List<Ingredient> getIngredientsForProduct(final Long productId);
}
