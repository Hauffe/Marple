package com.pucpr.alexandre.marple.bc;

import android.content.Context;
import android.widget.Toast;

import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.entity.RestrictionWithIngredients;
import com.pucpr.alexandre.marple.persistence.db.AppDatabase;

import java.util.List;

public class RestrictionBC {
    private static RestrictionBC instance = null;

    private List<Restriction> restrictions;
    private AppDatabase db;
    private Context context;

    public RestrictionBC() {

    }

    public static RestrictionBC sharedInstance() {

        if (instance == null)
            instance = new RestrictionBC();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
        db = AppDatabase.getInstance(context);
        updateRestrictionsList();
        restrictions = db.restrictionDAO().getAll();

    }

    public List<Restriction> getRestrictions() {
        updateRestrictionsList();
        return restrictions;
    }

    public Restriction getRestrictionByName(String name) {
        Restriction found_restriction = new Restriction();
        for(Restriction restriction : restrictions){
            if (restriction.getName().compareToIgnoreCase(name) == 0 ){
                found_restriction = restriction;
            }
        }
        return found_restriction;
    }

    private void updateRestrictionsList(){
        restrictions = db.restrictionDAO().getAll();
        for(Restriction restriction : restrictions){
            restrictions
                    .get(restriction.getId().intValue()-1)
                    .setIngredients(db.restrictionWithIngredientsDAO()
                            .getIngredientsForRestrictions(restriction.getId())
                    );
        }
    }

    public void saveRestriction(Restriction restriction){
            db.restrictionDAO().insertAll(restriction);
            restriction.setId(db.restrictionDAO().findByName(restriction.getName()).getId());
            for(Ingredient ingredient: restriction.getIngredients()){
                db.restrictionWithIngredientsDAO().insert(new RestrictionWithIngredients(restriction.getId(), ingredient.getId()));
            }

    }

    public void deleteRestriction(Restriction restriction){
        db.restrictionDAO().findByName(restriction.getName());
        for(Ingredient ingredient: restriction.getIngredients()){
            db.restrictionWithIngredientsDAO().delete(new RestrictionWithIngredients(restriction.getId(), ingredient.getId()));
        }
    }
}
