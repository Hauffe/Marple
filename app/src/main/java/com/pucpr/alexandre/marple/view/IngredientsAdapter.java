package com.pucpr.alexandre.marple.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.IngredientBC;
import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {

    private List<Ingredient> ingredients = IngredientBC.sharedInstance().getIngredients();
    private List<Ingredient> selectedIngredients;
    private Context context;

    public IngredientsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_ingredients, viewGroup, false);
        selectedIngredients = new ArrayList<>();

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

    public List<Ingredient> getSelectedIngredients() {
        return selectedIngredients;
    }

    public class IngredientsHolder extends RecyclerView.ViewHolder {

        TextView ingredient_name;
        TextView ingredient_desc;
        CheckBox ingredient_check;

        public IngredientsHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_check = itemView.findViewById(R.id.ingredient_check);

            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_desc = itemView.findViewById(R.id.ingredient_desc);

            ingredient_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedIngredients.add(IngredientBC.sharedInstance()
                                .getIngredientByName(ingredient_name.getText().toString()));

                        Toast.makeText(IngredientsAdapter.this.context,
                                Constants.ITEM_SELECTED + selectedIngredients.toString(),
                                Toast.LENGTH_LONG).show();
                    } else {

                        selectedIngredients.remove(IngredientBC.sharedInstance()
                                .getIngredientByName(ingredient_name.getText().toString()));

                        Toast.makeText(IngredientsAdapter.this.context,
                                Constants.ITEM_UNSELECTED + selectedIngredients.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}
