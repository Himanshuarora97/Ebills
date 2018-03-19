package com.ebills.alphamind.ebills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ebills.alphamind.ebills.Adapters.ProductsAdapter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by anmol on 20/3/18.
 */

public class ProductMainActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.LayoutManager l;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.products_main_activity);

        try {
            JSONArray jsonArray = new JSONArray(getIntent().getExtras().getString("products"));
            rv = findViewById(R.id.RecyclerViewProducts);
            l = new LinearLayoutManager(ProductMainActivity.this);
            adapter = new ProductsAdapter(ProductMainActivity.this , jsonArray);

            rv.setLayoutManager(l);
            rv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
