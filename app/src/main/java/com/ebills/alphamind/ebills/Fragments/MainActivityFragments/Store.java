package com.ebills.alphamind.ebills.Fragments.MainActivityFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebills.alphamind.ebills.Adapters.MainActivityShopNameFrontAdapter;
import com.ebills.alphamind.ebills.Adapters.ProductsAdapter;
import com.ebills.alphamind.ebills.Adapters.ShopPageActivity_HorizontalProductsAdapter;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Storage.AllBills.AllBillsStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anmol on 20/3/18.
 */

@SuppressLint("ValidFragment")
public class Store extends Fragment {


    Context ctx;

    private String query;

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @SuppressLint("ValidFragment")
    public Store(Context ctx, String query) {
        this.ctx = ctx;
        this.query = query;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.stores_fragment_main, container, false);

        try {
            AllBillsStorage allBillsStorage = new AllBillsStorage(ctx);
            JSONArray jsonArray = new JSONArray(allBillsStorage.getBillsSto());

            JSONArray jsonArray1 = new JSONArray();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getJSONObject("seller").getString("name").equals(query)) {
                    jsonArray1.put(jsonObject);
                }
                if (String.valueOf(Float.parseFloat(jsonObject.getJSONObject("invoice").getString("amount")) * (-1)).equals(query)) {
                    jsonArray1.put(jsonObject);
                }
                if (jsonObject.getJSONObject("invoice").getString("date").contains(query)) {
                    jsonArray1.put(jsonObject);
                }
            }

            rv = v.findViewById(R.id.RecyclerView_Stores);
            adapter = new ProductsAdapter(ctx, jsonArray1);
            layoutManager = new LinearLayoutManager(ctx);
            rv.setAdapter(adapter);
            rv.setLayoutManager(layoutManager);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;

    }

}
