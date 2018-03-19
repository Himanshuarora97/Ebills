package com.ebills.alphamind.ebills.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ebills.alphamind.ebills.MainActivity;
import com.ebills.alphamind.ebills.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by source on 3/15/18.
 */

public class FirebaseMessage extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("FCM notification");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());

        // Getting data
        JSONObject jsonObject = null;
        String pdf,html;
        try {
            jsonObject = new JSONObject(remoteMessage.getData().get("json"));
            pdf = remoteMessage.getData().get("pdf");
            html = remoteMessage.getData().get("html");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("onMessageReceived: ", remoteMessage.getData().toString());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }

}
