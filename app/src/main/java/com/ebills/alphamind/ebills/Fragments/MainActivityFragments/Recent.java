package com.ebills.alphamind.ebills.Fragments.MainActivityFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ebills.alphamind.ebills.Adapters.MainActivityRecentRecyclerAdapter;
import com.ebills.alphamind.ebills.LoginActivity;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Storage.OTPToken.Otptoken;
import com.ebills.alphamind.ebills.Storage.Recent.RecentBillStore;

import org.json.JSONArray;
import org.json.JSONException;


@SuppressLint("ValidFragment")
public class Recent extends Fragment {

    private static final String TAG = Recent.class.getSimpleName();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    TextView textView;

    Context context;

    //Locked
    TextView tx;
    View v;
    private boolean isAlreadyShow = false;
    private Otptoken otptoken;

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
        v = inflater.inflate(R.layout.activity_main_recent_fragment, container, false);

        // initialize
        initializeAll();


        // OTP
        otptoken = new Otptoken(context);
        checkCondition();
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.e(TAG, "isVisiableToUser: " + isVisibleToUser + "isAlreadyShow" + isAlreadyShow);
        if (isVisibleToUser) {
            if (!isAlreadyShow) {
                isAlreadyShow = true;
//                Log.e(TAG, "setUserVisibleHint: ");
            } else {
//                Log.e(TAG, "IsAlreadyShow: ");
                checkCondition();
            }
        }
    }

    public void checkCondition() {
        initializeAll();
        otptoken = new Otptoken(context);
        if (otptoken.getOTP().equals(" ")) {

            tx.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        } else {

            tx.setVisibility(View.GONE);
            RecentBillStore recentBillStore = new RecentBillStore(context);

            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(recentBillStore.getBills());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "checkCondition: " + jsonArray.toString());
            if (jsonArray.length() > 0) {
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                RecyclerView.Adapter adapter = new MainActivityRecentRecyclerAdapter(getActivity(), jsonArray);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            } else {
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }

    public void initializeAll() {
        recyclerView = v.findViewById(R.id.RVofrecentfragment);
        textView = v.findViewById(R.id.ifnoopenedbillsavailable);
        tx = v.findViewById(R.id.recentTextLocked);
    }
}


