package com.pucpr.alexandre.marple.bc;

import android.content.Context;

import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.entity.Product;
import com.pucpr.alexandre.marple.entity.ProductWithIngredients;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.persistence.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductBC {

    private static ProductBC instance = null;

    private List<Product> products;
    private List<Restriction> user_restrictions;
    private AppDatabase db;
    private Context context;

    public ProductBC() {
    }

    public static ProductBC sharedInstance() {

        if (instance == null)
            instance = new ProductBC();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
        db = AppDatabase.getInstance(context);
        verifyAndPopulateDatabase();
        updateProductsList();
        products = db.productDAO().getAll();
        user_restrictions = db.restrictionDAO().getAll();

    }

    private void verifyAndPopulateDatabase() {
        products = db.productDAO().getAll();
        if(products.isEmpty()){

            db.ingredientDAO().insertAll(new Ingredient("Ingrediente 1", "Um ingrediente aí"));
            db.ingredientDAO().insertAll(new Ingredient("Ingrediente 2", "Um ingrediente aí"));
            db.ingredientDAO().insertAll(new Ingredient("Ingrediente 3", "Um ingrediente aí"));
            db.ingredientDAO().insertAll(new Ingredient("Ingrediente 4", "Um ingrediente aí"));
            db.ingredientDAO().insertAll(new Ingredient("Ingrediente 5", "Um ingrediente aí"));
            db.ingredientDAO().insertAll(new Ingredient("Ingrediente 6", "Um ingrediente aí"));


            db.productDAO().insertAll(new Product("Produto 1"));
            db.productDAO().insertAll(new Product("Produto 2"));
            db.productDAO().insertAll(new Product("Produto 3"));

            List<Ingredient> ingredients = db.ingredientDAO().getAll();
            List<Product> products = db.productDAO().getAll();

            db.productWithIngredientsDAO().insert(
                    new ProductWithIngredients(
                            ingredients.get(0).getId(),
                            products.get(0).getId()));

            db.productWithIngredientsDAO().insert(
                    new ProductWithIngredients(
                            ingredients.get(1).getId(),
                            products.get(1).getId()));

            db.productWithIngredientsDAO().insert(
                    new ProductWithIngredients(
                            ingredients.get(2).getId(),
                            products.get(2).getId()));
        }
    }

    public List<Product> getProducts() {
        updateProductsList();
        return products;
    }
    public Product getProductByName(String name) {
        Product found_restriction = new Product();
        for(Product product : products){
            if (product.getName().compareToIgnoreCase(name) == 0 ){
                found_restriction = product;
            }
        }
        return found_restriction;
    }

    public List<Ingredient> getAllIngredientsFromRestrictions(){
        List<Ingredient> ingredients = new ArrayList<>();
        if(!user_restrictions.isEmpty()) {
            for (Restriction restriction : user_restrictions) {
                ingredients.addAll(db.restrictionWithIngredientsDAO()
                        .getIngredientsForRestrictions(restriction.getId()));
            }
        }
        return ingredients;
    }

    private void updateProductsList(){
        products = db.productDAO().getAll();
        for(Product product : products){
            product
                    .setIngredients(db.productWithIngredientsDAO()
                            .getIngredientsForProduct(product.getId())
                    );
        }
    }


    public void saveProduct(Product product){
        db.productDAO().insertAll(product);
        product.setId(db.productDAO().findByName(product.getName()).getId());
        for(Ingredient ingredient: product.getIngredients()){
            db.productWithIngredientsDAO()
                    .insert(new ProductWithIngredients(product.getId(), ingredient.getId()));
        }

    }

    public void deleteProduct(Product product){
        for(Ingredient ingredient: product.getIngredients()){
            db.productWithIngredientsDAO()
                    .delete(new ProductWithIngredients(product.getId(), ingredient.getId()));
        }
        db.productDAO().delete(product);
    }

}
