package com.ebills.alphamind.ebills;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

/**
 * Created by anmol on 14/3/18.
 */

public class SearchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // setContentView(R.layout.);

        setupWindowAnimations();

    }

    // Query String
    // Search Here
    public void doMySearch(String query){

        System.out.println(query);
    }


    //setting up animation
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

}
