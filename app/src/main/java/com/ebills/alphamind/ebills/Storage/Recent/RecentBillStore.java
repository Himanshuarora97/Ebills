package com.ebills.alphamind.ebills.Storage.Recent;

import android.content.Context;
import android.content.SharedPreferences;

import com.ebills.alphamind.ebills.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anmol on 13/3/18.
 */

public class RecentBillStore {

    Context ctx;

    public RecentBillStore(Context ctx){
        this.ctx = ctx;
    }



    public void saveBill(String pName , String sName , String priceName) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product_name" , pName);
        jsonObject.put("shop_name" , sName);
        jsonObject.put("price_name" , priceName);

        JSONArray jsonArray = new JSONArray(getBills());

        jsonArray.put(jsonObject);

        String r = jsonArray.toString();

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.RecentBills), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.RecentBills), r);
        System.out.println("-----------------------------------------> Success saved");
        editor.commit();
    }

    public String getBills(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.RecentBills)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.RecentBills), "");
    }

}
