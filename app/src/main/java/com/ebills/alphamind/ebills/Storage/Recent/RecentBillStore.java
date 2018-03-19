package com.ebills.alphamind.ebills.Storage.Recent;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ebills.alphamind.ebills.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class RecentBillStore {

    Context ctx;

    public RecentBillStore(Context ctx){
        this.ctx = ctx;
    }

    public void saveBill(JSONObject jsonObject) throws JSONException {

        String arr = getBills();

        JSONArray jsonArray = new JSONArray(arr);

        jsonArray.put(jsonObject);

        Log.e("saveBill: ",jsonArray.toString() );
        String r = jsonArray.toString();

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.RecentBills), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.RecentBills), r);
        System.out.println("-----------------------------------------> Success saved");
        editor.apply();
    }

    public String getBills(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.RecentBills)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.RecentBills), "[]");
    }

}
