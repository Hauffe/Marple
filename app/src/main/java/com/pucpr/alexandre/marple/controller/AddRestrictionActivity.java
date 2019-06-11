package com.pucpr.alexandre.marple.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.IngredientBC;
import com.pucpr.alexandre.marple.entity.Ingredient;
import com.pucpr.alexandre.marple.view.IngredientsAdapter;

public class AddRestrictionActivity extends AppCompatActivity {

    private IngredientsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restriction);
        IngredientBC.sharedInstance().setContext(this);

        recyclerView = findViewById(R.id.lstIngredients);
        adapter = new IngredientsAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }

}
