package com.ebills.alphamind.ebills.Fragments.MainActivityFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebills.alphamind.ebills.R;

/**
 * Created by anmol on 13/3/18.
 */

public class allBills extends Fragment {

    public allBills() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main_allbills_fragment, container, false);
    }

}