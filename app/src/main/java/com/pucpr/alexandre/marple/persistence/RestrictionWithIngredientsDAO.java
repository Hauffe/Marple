package com.pucpr.alexandre.marple.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.entity.RestrictionWithIngredients;

import java.util.List;

@Dao
public interface RestrictionWithIngredientsDAO {

    @Insert
    void insert(RestrictionWithIngredients restrictionWithIngredients);

    @Delete
    void delete(RestrictionWithIngredients restrictionWithIngredients);

    @Query("DELETE FROM restriction_has_ingredients")
    int clean();

    @Query("SELECT * FROM restriction " +
            "INNER JOIN restriction_has_ingredients " +
            "ON restriction.id=restriction_has_ingredients.restrictionId " +
            "WHERE restriction_has_ingredients.ingredientId=:ingredientId")
    List<Restriction> getRestrictionsForIngredients(final Long ingredientId);

    @Query("SELECT * FROM ingredient " +
            "INNER JOIN restriction_has_ingredients " +
            "ON ingredient.id=restriction_has_ingredients.ingredientId " +
            "WHERE restriction_has_ingredients.restrictionId=:restrictionId")
    List<Ingredient> getIngredientsForRestrictions(final Long restrictionId);
}
