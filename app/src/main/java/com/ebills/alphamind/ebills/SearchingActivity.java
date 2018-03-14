package com.ebills.alphamind.ebills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.widget.TextView;

/**
 * Created by anmol on 14/3/18.
 */

public class SearchingActivity extends AppCompatActivity {

    private TextView searchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.search_main_activity);

        // Intializing
        Intialize();

        // Query
        String query = getIntent().getExtras().getString("query");

        doMySearch(query);

        setupWindowAnimations();

    }

    //Intializing
    private void Intialize(){
        searchText = findViewById(R.id.SearchMainText);
    }



    // Query String
    // Search Here
    public void doMySearch(String query){

        searchText.setText(query);

    }


    //setting up animation
    private void setupWindowAnimations() {
        Slide fade = new Slide();
        fade.setDuration(300);
        getWindow().setEnterTransition(fade);
    }

}
