package com.pucpr.alexandre.marple.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.ProductBC;
import com.pucpr.alexandre.marple.bc.RestrictionBC;
import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.entity.Product;
import com.pucpr.alexandre.marple.utils.Constants;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private List<Product> products = ProductBC.sharedInstance().getProducts();
    private List<Ingredient> ingredients = RestrictionBC.sharedInstance().getAllIngredientsFromRestrictions();
    private Context context;

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_products, viewGroup, false);

        return new ProductsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsHolder productsHolder, int position) {
        Product product = products.get(position);

        productsHolder.product_title_name.setText(product.getName());
        productsHolder.product_description.setText(product.getIngredients().toString());
        productsHolder.product_image.setImageResource(R.drawable.produto_1);


        if(!ingredients.isEmpty()){
            for(Ingredient ingredient : ingredients) {
                for(Ingredient product_ingredient : product.getIngredients()) {
                    if (product_ingredient.getId() == ingredient.getId()) {
                        productsHolder.product_card.setBackgroundColor(ContextCompat.getColor(context, R.color.notAllowedProduct));
                        productsHolder.consumable.setText(Constants.PRODUCT_NOT_ALLOWED);
                    }
                }
            }
        }


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductsHolder extends RecyclerView.ViewHolder {
        TextView product_title_name;
        TextView product_description;
        TextView consumable;
        ImageView product_image;
        CardView product_card;


        public ProductsHolder(@NonNull View itemView) {
            super(itemView);
            product_title_name = itemView.findViewById(R.id.product_title_name);
            product_description = itemView.findViewById(R.id.product_description);
            consumable = itemView.findViewById(R.id.consumable);
            product_image = itemView.findViewById(R.id.product_image);
            product_card = itemView.findViewById(R.id.product_card);

        }
    }
}
