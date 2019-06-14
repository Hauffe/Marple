package com.pucpr.alexandre.marple.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.IngredientBC;
import com.pucpr.alexandre.marple.bc.RestrictionBC;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.activities.adapters.IngredientsAdapter;

public class AddRestrictionActivity extends AppCompatActivity {

    private IngredientsAdapter adapter;
    private RecyclerView recyclerView;
    private EditText restriction_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restriction);
        IngredientBC.sharedInstance().setContext(this);

        recyclerView = findViewById(R.id.lstIngredients);
        adapter = new IngredientsAdapter(this);
        recyclerView.setAdapter(adapter);
        restriction_name = findViewById(R.id.input_restriction_name);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }

    public void save_restriction(View view) {
        Intent intent = new Intent(this, RestrictionActivity.class);
        Restriction restriction = new Restriction(restriction_name.getText().toString());
        restriction.setIngredients(adapter.getSelectedIngredients());
        RestrictionBC.sharedInstance().saveRestriction(restriction);
        startActivity(intent);
    }

}
