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
import android.widget.TextView;

import com.ebills.alphamind.ebills.Adapters.MainActivityRecentRecyclerAdapter;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Storage.Recent.RecentBillStore;

import org.json.JSONArray;
import org.json.JSONException;


@SuppressLint("ValidFragment")
public class Recent extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    TextView textView;

    Context context;


    @SuppressLint("ValidFragment")
    public Recent(Context ctx) {
        this.context = ctx;
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

        RecentBillStore recentBillStore = new RecentBillStore(context);

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(recentBillStore.getBills());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (jsonArray.length() > 0) {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            RecyclerView.Adapter adapter = new MainActivityRecentRecyclerAdapter(jsonArray);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        return v;
    }

    public void initializeAll(View v) {
        recyclerView = v.findViewById(R.id.RVofrecentfragment);
        textView = v.findViewById(R.id.ifnoopenedbillsavailable);
    }
}


