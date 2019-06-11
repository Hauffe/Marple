package com.pucpr.alexandre.marple.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.IngredientBC;
import com.pucpr.alexandre.marple.entity.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {

    private List<Ingredient> ingredients = IngredientBC.sharedInstance().getIngredients();

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_ingredients, viewGroup, false);

        return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder ingredientsHolder, int position) {
        Ingredient ingredient = ingredients.get(position);

        ingredientsHolder.ingredient_name.setText(ingredient.getName());
        ingredientsHolder.ingredient_desc.setText(ingredient.getDescription());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientsHolder extends RecyclerView.ViewHolder {

        TextView ingredient_name;
        TextView ingredient_desc;

        public IngredientsHolder(@NonNull View itemView) {
            super(itemView);

            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_desc = itemView.findViewById(R.id.ingredient_desc);
        }
    }
}
