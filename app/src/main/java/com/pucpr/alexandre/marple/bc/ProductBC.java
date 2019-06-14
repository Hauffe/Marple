package com.pucpr.alexandre.marple.bc;

import android.content.Context;

import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.entity.Product;
import com.pucpr.alexandre.marple.entity.ProductWithIngredients;
import com.pucpr.alexandre.marple.persistence.db.AppDatabase;

import java.util.List;

public class ProductBC {

    private static ProductBC instance = null;

    private List<Product> products;
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
        updateRestrictionsList();
        products = db.productDAO().getAll();

    }

    public List<Product> getProducts() {
        updateRestrictionsList();
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

    private void updateRestrictionsList(){
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
