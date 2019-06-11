package com.pucpr.alexandre.marple.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pucpr.alexandre.marple.R;

public class RestrictionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrictions);

    }


    public void add_restriction(View view) {
        Intent intent = new Intent(this, AddRestrictionActivity.class);
        startActivity(intent);
    }
}
