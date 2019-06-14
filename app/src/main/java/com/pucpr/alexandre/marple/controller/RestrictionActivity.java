package com.pucpr.alexandre.marple.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.RestrictionBC;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.view.RestrictionsAdapter;

public class RestrictionActivity extends AppCompatActivity {

    private RestrictionsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrictions);
        RestrictionBC.sharedInstance().setContext(this);

        recyclerView = findViewById(R.id.lstRestrictions);
        adapter = new RestrictionsAdapter(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }


    public void add_restriction(View view) {
        Intent intent = new Intent(this, AddRestrictionActivity.class);
        startActivity(intent);
    }
}
