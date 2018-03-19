package com.ebills.alphamind.ebills.Server;

import org.json.JSONException;
import org.json.JSONObject;

public class GetData {

    //Constructor
    JSONObject jsonObject;

    public void  saveJson(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public interface getDetails{
        void getD(JSONObject jsonObject) throws JSONException;
    }

}
