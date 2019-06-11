package com.pucpr.alexandre.marple.persistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pucpr.alexandre.marple.persistence.IngredientDAO;
import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.utils.Constants;

@Database(entities = {Ingredient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract IngredientDAO ingredientDAO();

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
