package com.ebills.alphamind.ebills.Server;

import android.content.Context;

import org.json.JSONObject;

public class GetData {

    //Constructor
    JSONObject jsonObject;

    public void  saveJson(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public void getDeta(final getDetails getDetails){
        getDetails.getD(jsonObject);
    }

    public interface getDetails{
        void getD(JSONObject jsonObject);
    }

}
