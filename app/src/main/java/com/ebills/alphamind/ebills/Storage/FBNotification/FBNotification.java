package com.ebills.alphamind.ebills.Storage.FBNotification;

import android.content.Context;
import android.content.SharedPreferences;

import com.ebills.alphamind.ebills.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Stack;

/**
 * Created by anmol on 19/3/18.
 */

public class FBNotification {

    Context ctx;

    public FBNotification(Context ctx){
        this.ctx = ctx;
    }

    public void saveNotification(JSONObject jsonObject) throws JSONException {

        String j = jsonObject.toString();

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.FB_NOTIFICATION), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.FB_NOTIFICATION), j);
        System.out.println("-----------------------------------------> Success saved");
        editor.apply();
    }

    public String getNotification(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.FB_NOTIFICATION)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.FB_NOTIFICATION), "{}");
    }


}
