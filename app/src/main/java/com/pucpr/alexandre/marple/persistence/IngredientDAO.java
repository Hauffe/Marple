package com.pucpr.alexandre.marple.persistence;

import android.arch.persistence.room.*;

import com.pucpr.alexandre.marple.entity.Ingredient;

import java.util.List;

@Dao
public interface IngredientDAO {
    @Query("SELECT * FROM ingredient")
    List<Ingredient> getAll();

    @Query("SELECT * FROM ingredient WHERE id IN (:ingredientIds)")
    List<Ingredient> loadAllByIds(int[] ingredientIds);

    @Query("SELECT * FROM ingredient WHERE name LIKE :name LIMIT 1")
    Ingredient findByName(String name);

    @Query("DELETE FROM ingredient")
    int clean();

    @Insert
    void insertAll(Ingredient... ingredients);

    @Delete
    void delete(Ingredient ingredients);
}
