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

import com.ebills.alphamind.ebills.Adapters.mainActivityAllBillsRecyclerAdapter;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Server.allBillsServer;

import org.json.JSONArray;
import org.json.JSONException;


//stores , bills

// pn ,pdesc ,  , sn , price , saddress , sdescription

@SuppressLint("ValidFragment")
public class allBills extends Fragment {

    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    TextView tx;

    Context ctx;


    @SuppressLint("ValidFragment")
    public allBills(Context ctx) {
        // Required empty public constructor
        this.ctx = ctx;
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

        allBillsServer billServer = new allBillsServer(ctx);

        // Getting Results from bills Server
        try {
            billServer.getResults(new allBillsServer.BillsCallBack() {
                @Override
                public void getBillDetails(JSONArray jsonArray) throws JSONException {

                    if (jsonArray.length()>0){
                        tx.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ctx);
                        RecyclerView.Adapter adapter=new mainActivityAllBillsRecyclerAdapter(jsonArray);
                        rv.setLayoutManager(layoutManager);
                        rv.setAdapter(adapter);
                    }
                    else{
                        tx.setVisibility(View.VISIBLE);
                        rv.setVisibility(View.GONE);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;
    }

    public void initializeAll(View v){
        rv = v.findViewById(R.id.RVofallbillsfragment);
        tx = v.findViewById(R.id.ifnoallbillsavailable);
    }

}