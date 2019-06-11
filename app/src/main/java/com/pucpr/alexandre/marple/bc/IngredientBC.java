package com.pucpr.alexandre.marple.bc;

import com.pucpr.alexandre.marple.controller.AddRestrictionActivity;
import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.persistence.db.AppDatabase;

import java.util.List;

public class IngredientBC {
    private static IngredientBC instance = null;

    private List<Ingredient> ingredients;
    private AppDatabase db;
    private AddRestrictionActivity context;

    public IngredientBC() {

    }

    public static IngredientBC sharedInstance() {

        if (instance == null)
            instance = new IngredientBC();

        return instance;
    }

    public void setContext(AddRestrictionActivity context) {

        this.context = context;
        db = AppDatabase.getInstance(context);
        db.ingredientDAO().clean();
        db.ingredientDAO().insertAll(new Ingredient("Ingrediente 1", "Um ingrediente aí"));
        db.ingredientDAO().insertAll(new Ingredient("Ingrediente 2", "Um ingrediente aí"));
        db.ingredientDAO().insertAll(new Ingredient("Ingrediente 3", "Um ingrediente aí"));
        db.ingredientDAO().insertAll(new Ingredient("Ingrediente 4", "Um ingrediente aí"));
        db.ingredientDAO().insertAll(new Ingredient("Ingrediente 5", "Um ingrediente aí"));
        db.ingredientDAO().insertAll(new Ingredient("Ingrediente 6", "Um ingrediente aí"));

        ingredients = db.ingredientDAO().getAll();

    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }




}
