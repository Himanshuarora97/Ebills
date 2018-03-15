package com.ebills.alphamind.ebills.Server;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class AllBillsServer {

    Context context;

    // interface for getting array
    public interface BillsCallBack{
        void getBillDetails(JSONArray jsonArray) throws JSONException;
    }

    public AllBillsServer(Context ctx){
        this.context = ctx;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("allbills.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // Get Results
    public void getResults(BillsCallBack billsCallBack) throws JSONException {
        JSONObject obj = new JSONObject(loadJSONFromAsset());
        JSONArray m_jArry = obj.getJSONArray("Results");
        billsCallBack.getBillDetails(m_jArry);
    }

}


