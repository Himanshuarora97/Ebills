package com.ebills.alphamind.ebills;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

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
    private Toolbar toolbar;
    String pdfLink, htmlLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.products_main_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            JSONArray jsonArray = new JSONArray(getIntent().getExtras().getString("products"));
            pdfLink = getIntent().getExtras().getString("pdf");
            htmlLink = getIntent().getExtras().getString("html");
            rv = findViewById(R.id.RecyclerViewProducts);
            l = new LinearLayoutManager(ProductMainActivity.this);
            adapter = new ProductsAdapter(ProductMainActivity.this, jsonArray);

            rv.setLayoutManager(l);
            rv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_product, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pdf:
                Intent intent = new Intent(ProductMainActivity.this, ShowingBillActivity.class);
                intent.putExtra("pdf", pdfLink);
                startActivity(intent);
                break;
            case R.id.html:
                intent = new Intent(ProductMainActivity.this, ShowingBillActivity.class);
                intent.putExtra("html", htmlLink);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
