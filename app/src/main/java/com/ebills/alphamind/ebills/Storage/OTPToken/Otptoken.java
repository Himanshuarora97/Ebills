package com.ebills.alphamind.ebills.Storage.OTPToken;

import android.content.Context;
import android.content.SharedPreferences;

import com.ebills.alphamind.ebills.R;
import org.json.JSONException;

/**
 * Created by anmol on 16/3/18.
 */

public class Otptoken {

    Context ctx;

    public Otptoken(Context ctx){
        this.ctx = ctx;
    }

    public void saveOTP(String token) throws JSONException {

        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf(R.string.OTP_TOKEN), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(String.valueOf(R.string.OTP_TOKEN), token);
        System.out.println("-----------------------------------------> Success saved");
        editor.commit();
    }

    public String getOTP(){
        SharedPreferences sharedPref = ctx.getSharedPreferences(String.valueOf((R.string.OTP_TOKEN)), Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(R.string.OTP_TOKEN), " ");
    }
}
