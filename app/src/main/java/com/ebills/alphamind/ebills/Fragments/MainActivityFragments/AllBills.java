package com.ebills.alphamind.ebills.Fragments.MainActivityFragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebills.alphamind.ebills.Adapters.MainActivityShopNameFrontAdapter;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Server.GetData;
import com.ebills.alphamind.ebills.Storage.AllBills.AllBillsStorage;
import com.ebills.alphamind.ebills.Storage.OTPToken.Otptoken;
import com.ebills.alphamind.ebills.utils.ServiceCallbacks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//stores , bills

// pn ,pdesc ,  , sn , price , saddress , sdescription

@SuppressLint("ValidFragment")
public class AllBills extends Fragment implements GetData.getDetails, ServiceCallbacks {

    private static final String TAG = AllBills.class.getSimpleName();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    TextView textView;

    Context context;

    // Locked TextView
    TextView tx;

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


        try {
            checkCondition();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;
    }

    public void checkCondition() throws JSONException {

        Otptoken otptoken = new Otptoken(context);
        if (otptoken.getOTP().equals(" ")) {
            tx.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        } else {
            tx.setVisibility(View.GONE);
//            AllBillsServer billServer = new AllBillsServer(context);
//            // Getting Results from bills Server
//            try {
//                billServer.getResults(new AllBillsServer.BillsCallBack() {
//                    @Override
//                    public void getBillDetails(JSONArray jsonArray) throws JSONException {
//
//                        if (jsonArray.length() > 0) {
//                            textView.setVisibility(View.GONE);
//                            recyclerView.setVisibility(View.VISIBLE);
//                            Log.e("onBindViewHolder: ", String.valueOf(jsonArray));
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//                            RecyclerView.Adapter adapter = new ProductsAdapter(context , jsonArray);
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setAdapter(adapter);
//                        } else {
//                            textView.setVisibility(View.VISIBLE);
//                            recyclerView.setVisibility(View.GONE);
//                        }
//                    }
//                });
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            AllBillsStorage allBillsStorage = new AllBillsStorage(context);
            JSONArray jsonArray = new JSONArray(allBillsStorage.getBillsSto());
            if (jsonArray.length() > 0) {
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                Log.e("onBindViewHolder: ", String.valueOf(jsonArray));
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                RecyclerView.Adapter adapter = new MainActivityShopNameFrontAdapter(context, jsonArray);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }

    }

    public void initializeAll(View v) {
        recyclerView = v.findViewById(R.id.RVofallbillsfragment);
        textView = v.findViewById(R.id.ifnoallbillsavailable);
        tx = v.findViewById(R.id.AllBillsLocked);
    }

    @Override
    public void getD(JSONObject jsonObject) throws JSONException {
        Log.e("Okay", "Yes it is doing............................");
        AllBillsStorage allBillsStorage = new AllBillsStorage(context);
        allBillsStorage.saveJSONObject(jsonObject);
    }

    @Override
    public void doSomething() {
        Log.e("TAG", "doSomething: ");
        try {
            checkCondition();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver((mMessageReceiver),
                new IntentFilter("data")
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Log.e(TAG, "onReceive: ");
                checkCondition();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}