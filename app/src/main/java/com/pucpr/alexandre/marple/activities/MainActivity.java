package com.pucpr.alexandre.marple.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.activities.adapters.ProductsAdapter;
import com.pucpr.alexandre.marple.bc.ProductBC;
import com.pucpr.alexandre.marple.bc.RestrictionBC;
import com.pucpr.alexandre.marple.entity.Product;
import com.pucpr.alexandre.marple.entity.Restriction;

public class MainActivity extends AppCompatActivity {

    private ProductsAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductBC.sharedInstance().setContext(this);
        RestrictionBC.sharedInstance().setContext(this);

        recyclerView = findViewById(R.id.lstProducts);
        adapter = new ProductsAdapter(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_restrictions:
                openRestrictions();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void openRestrictions() {
        Intent intent = new Intent(this, RestrictionActivity.class);
        startActivity(intent);
    }
}
