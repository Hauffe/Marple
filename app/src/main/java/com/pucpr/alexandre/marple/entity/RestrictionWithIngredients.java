package com.pucpr.alexandre.marple.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.util.List;


@Entity(tableName = "restriction_has_ingredients",
        primaryKeys = { "restrictionId", "ingredientId" },
        foreignKeys = {
                @ForeignKey(entity = Restriction.class,
                        parentColumns = "id",
                        childColumns = "restrictionId"),
                @ForeignKey(entity = Ingredient.class,
                        parentColumns = "id",
                        childColumns = "ingredientId")
        })
public class RestrictionWithIngredients {

    @NonNull
    public final Long restrictionId;
    @NonNull
    public final Long ingredientId;

    public RestrictionWithIngredients(Long restrictionId, Long ingredientId) {
        this.restrictionId = restrictionId;
        this.ingredientId = ingredientId;
    }
}
