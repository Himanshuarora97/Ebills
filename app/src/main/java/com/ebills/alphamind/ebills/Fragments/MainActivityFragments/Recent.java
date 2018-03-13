package com.ebills.alphamind.ebills.Fragments.MainActivityFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebills.alphamind.ebills.Adapters.mainActivityAllBillsRecyclerAdapter;
import com.ebills.alphamind.ebills.Adapters.mainActivityRecentRecyclerAdapter;
import com.ebills.alphamind.ebills.R;


public class Recent extends Fragment {

    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    TextView tx;

    Context ctx;


    // Boolean assumption (Checking from response of server
    boolean a = true;



    public Recent() {
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
        View v = inflater.inflate(R.layout.activity_main_recent_fragment, container, false);

        // initialize
        initializeAll(v);


        if (a){
            tx.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ctx);
            RecyclerView.Adapter adapter=new mainActivityRecentRecyclerAdapter();
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(adapter);
        }
        else{
            tx.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }
        return v;
    }

    public void initializeAll(View v){
        rv = v.findViewById(R.id.RVofrecentfragment);
        tx = v.findViewById(R.id.ifnoopenedbillsavailable);
    }
}


