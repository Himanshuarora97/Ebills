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

import com.ebills.alphamind.ebills.Adapters.MainActivityAllBillsRecyclerAdapter;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Server.AllBillsServer;

import org.json.JSONArray;
import org.json.JSONException;


//stores , bills

// pn ,pdesc ,  , sn , price , saddress , sdescription

@SuppressLint("ValidFragment")
public class AllBills extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    TextView textView;

    Context context;


    @SuppressLint("ValidFragment")
    public AllBills(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_main_allbills_fragment, container, false);

        // initialize
        initializeAll(v);

        AllBillsServer billServer = new AllBillsServer(context);

        // Getting Results from bills Server
        try {
            billServer.getResults(new AllBillsServer.BillsCallBack() {
                @Override
                public void getBillDetails(JSONArray jsonArray) throws JSONException {

                    if (jsonArray.length() > 0) {
                        textView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        RecyclerView.Adapter adapter = new MainActivityAllBillsRecyclerAdapter(jsonArray);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    } else {
                        textView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;
    }

    public void initializeAll(View v) {
        recyclerView = v.findViewById(R.id.RVofallbillsfragment);
        textView = v.findViewById(R.id.ifnoallbillsavailable);
    }

}