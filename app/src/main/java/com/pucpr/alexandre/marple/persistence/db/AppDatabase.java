package com.pucpr.alexandre.marple.persistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pucpr.alexandre.marple.entity.Product;
import com.pucpr.alexandre.marple.entity.ProductWithIngredients;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.entity.RestrictionWithIngredients;
import com.pucpr.alexandre.marple.persistence.IngredientDAO;
import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.persistence.ProductDAO;
import com.pucpr.alexandre.marple.persistence.ProductWithIngredientsDAO;
import com.pucpr.alexandre.marple.persistence.RestrictionWithIngredientsDAO;
import com.pucpr.alexandre.marple.persistence.RestrictionsDAO;
import com.pucpr.alexandre.marple.utils.Constants;

@Database(entities = {
        Ingredient.class,
        Restriction.class,
        Product.class,
        RestrictionWithIngredients.class,
        ProductWithIngredients.class
    }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract IngredientDAO ingredientDAO();
    public abstract RestrictionsDAO restrictionDAO();
    public abstract ProductDAO productDAO();
    public abstract RestrictionWithIngredientsDAO restrictionWithIngredientsDAO();
    public abstract ProductWithIngredientsDAO productWithIngredientsDAO();

    private static AppDatabase appDb;

    public static AppDatabase getInstance(Context context) {
        if(appDb == null)  appDb = buildDatabaseInstance(context);
        return appDb;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        appDb = null;
    }

}
