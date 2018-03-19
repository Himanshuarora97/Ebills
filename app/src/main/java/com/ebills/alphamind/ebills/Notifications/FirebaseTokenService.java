package com.ebills.alphamind.ebills.Notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ebills.alphamind.ebills.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by source on 3/15/18.
 */
public class FirebaseTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "REG TOKEN";
    public static final String PREFS = "PREFS_TOKEN";
    public static final String TOKEN = "TOKEN";


    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TOKEN", refreshToken);
        editor.apply();
        Log.e(TAG, refreshToken);
    }
}

