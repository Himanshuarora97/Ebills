package com.ebills.alphamind.ebills.Storage.FBNotification;

import android.content.Context;
import android.content.SharedPreferences;

import com.ebills.alphamind.ebills.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by anmol on 19/3/18.
 */

public class FBNotification {

    Context ctx;

    public FBNotification(Context ctx){
        this.ctx = ctx;
    }

    public void saveNotification(String html,String pdf) throws JSONException {

        JSONArray jsonArray = new JSONArray(getNotification());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("HTML" , html);
        jsonObject.put("PDF" , pdf);

        jsonArray.put(jsonObject);

        String js = jsonArray.toString();

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.FB_NOTIFICATION), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.FB_NOTIFICATION), js);
        System.out.println("-----------------------------------------> Success saved");
        editor.apply();
    }

    public String getNotification(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.FB_NOTIFICATION)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.FB_NOTIFICATION), "[]");
    }
}
