package com.pucpr.alexandre.marple.bc;

import android.content.Context;

import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.persistence.db.AppDatabase;

import java.util.List;

public class IngredientBC {
    private static IngredientBC instance = null;

    private List<Ingredient> ingredients;
    private AppDatabase db;
    private Context context;

    public IngredientBC() {

    }

    public static IngredientBC sharedInstance() {

        if (instance == null)
            instance = new IngredientBC();

        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        db = AppDatabase.getInstance(context);
        ingredients = db.ingredientDAO().getAll();
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredientByName(String name) {
        Ingredient found_ingredient = new Ingredient();
        for(Ingredient ingredient : ingredients){
            if (ingredient.getName().compareToIgnoreCase(name) == 0 ){
                found_ingredient = ingredient;
            }
        }
        return found_ingredient;
    }

}
