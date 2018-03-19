package com.ebills.alphamind.ebills.Storage.AllBills;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ebills.alphamind.ebills.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anmol on 19/3/18.
 */

public class AllBillsStorage {


    Context ctx;

    public AllBillsStorage(Context ctx){
        this.ctx = ctx;
    }

    public void saveJSONObject(JSONObject jsonObject) throws JSONException {

        JSONArray jsonArray = new JSONArray(getBillsSto());
        jsonArray.put(jsonObject);
        String l = jsonArray.toString();
        Log.e("allbillstorage" , l);
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.AllBills_Storage), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.AllBills_Storage), l);
        System.out.println("-----------------------------------------> Success saved");
        editor.commit();
    }

    public String getBillsSto(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.AllBills_Storage)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.AllBills_Storage), "[]");
    }
}
