package com.ebills.alphamind.ebills.Notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by source on 3/15/18.
 */
public class FirebaseTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "REG TOKEN";
    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, refreshToken);
    }
}

