package com.ebills.alphamind.ebills.Storage.CacheStorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.ebills.alphamind.ebills.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Stack;


public class CacheStorage {

    Context ctx;

    public CacheStorage(Context ctx){
        this.ctx = ctx;
    }

    public void saveFile(String path) throws JSONException {

        String pth = getFile();
        Type listType =
                new TypeToken<Stack<String>>(){}.getType();
        Stack<String> stack = new Gson().fromJson(pth, listType);

        if (stack.size()>5){
            stack.pop();
            stack.push(path);
        }
        else{
            stack.push(path);
        }

        String json = new Gson().toJson(stack);

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.Cache_Storage_EBILLS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.Cache_Storage_EBILLS), json);
        System.out.println("-----------------------------------------> Success saved");
        editor.commit();
    }

    public String getFile(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.Cache_Storage_EBILLS)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.Cache_Storage_EBILLS), "[]");
    }

}
