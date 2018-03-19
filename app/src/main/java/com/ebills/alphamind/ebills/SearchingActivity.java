package com.ebills.alphamind.ebills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.widget.TextView;

import com.ebills.alphamind.ebills.Storage.AllBills.AllBillsStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anmol on 14/3/18.
 */

public class SearchingActivity extends AppCompatActivity {


    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.search_main_activity);

        // Intializing
        Intialize();

        // Query
        String query = getIntent().getExtras().getString("query");

        try {
            doMySearch(query);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setupWindowAnimations();

    }

//    //Intializing
//    private void Intialize(){
//        searchText = findViewById(R.id.SearchMainText);
//    }


    public void Intialize(){
        rv = findViewById(R.id.SearchRecyclerView);
        layoutManager = new LinearLayoutManager(SearchingActivity.this);

    }

    // Query String
    // Search Here
    public void doMySearch(String query) throws JSONException {

//        searchText.setText(query);

        AllBillsStorage allBillsStorage = new AllBillsStorage(SearchingActivity.this);
        JSONArray jsonArray = new JSONArray(allBillsStorage.getBillsSto());

        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();

        for (int i = 0 ; i<jsonArray.length() ;i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getJSONObject("seller").getString("name").equals(query)){
                arr.add(jsonObject);
            }
            if (String.valueOf(Integer.parseInt(jsonObject.getJSONObject("invoice").getString("amount")) * (-1)).equals(query))){
                arr.add(jsonObject);
            }
            if (jsonObject.getJSONObject("invoice").getString("date").contains(query)){
                arr.add(jsonObject);
            }
            JSONArray jsonArray1 = jsonObject.getJSONObject("invoice").getJSONArray("products");

            for (int i = 0 ; i<jsonArray1.length() ; i++){
                if (jsonArray1.getJSONObject(i).getString("STOCKITEMNAME").equals(query)){
                    arr.add(jsonObject);
                }
            }
        }





    }


    //setting up animation
    private void setupWindowAnimations() {
        Slide fade = new Slide();
        fade.setDuration(300);
        getWindow().setEnterTransition(fade);
    }

}
