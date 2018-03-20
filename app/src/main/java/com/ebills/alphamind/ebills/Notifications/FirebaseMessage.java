package com.ebills.alphamind.ebills.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.ebills.alphamind.ebills.MainActivity;
import com.ebills.alphamind.ebills.R;
import com.ebills.alphamind.ebills.Server.GetData;
import com.ebills.alphamind.ebills.Storage.AllBills.AllBillsStorage;
import com.ebills.alphamind.ebills.Storage.FBNotification.FBNotification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by source on 3/15/18.
 */

public class FirebaseMessage extends FirebaseMessagingService {

    // Getting data
    String pdf, html;
    private LocalBroadcastManager broadcaster;
    JSONObject messageObject;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("FCM notification");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());

        try {
            messageObject = new JSONObject(remoteMessage.getData());
            Log.e("onMessageReceived: ", messageObject.toString());
//            jsonObject = messageObject.getJSONObject("json");
            pdf = messageObject.getString("pdf");
            html = remoteMessage.getData().get("html");
            JSONObject jsonObject = new JSONObject(messageObject.getString("json"));
            Log.e("onMessageReceived: ", jsonObject.toString());
            FBNotification fbNotification = new FBNotification(getApplicationContext());
            fbNotification.saveNotification(html, pdf);
            AllBillsStorage allBillsStorage = new AllBillsStorage(getApplicationContext());
            allBillsStorage.saveJSONObject(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("onMessageReceived: ", remoteMessage.getData().toString());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

        Intent intent1 = new Intent("data");
        broadcaster.sendBroadcast(intent1);
    }

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

}
