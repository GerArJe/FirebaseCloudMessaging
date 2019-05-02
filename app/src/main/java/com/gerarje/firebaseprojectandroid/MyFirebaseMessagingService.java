package com.gerarje.firebaseprojectandroid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.gerarje.firebaseprojectandroid.model.MyNotification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyMessagingService";
    private static final String KEY_DECOUNT = "descount_key";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("your token",s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        MyNotification myNotification = new MyNotification();
        myNotification.setId(remoteMessage.getFrom());
        myNotification.setTitle(remoteMessage.getNotification().getTitle());
        myNotification.setDescription(remoteMessage.getNotification().getBody());
        myNotification.setDescount(remoteMessage.getData().get(KEY_DECOUNT));

        showNotification(myNotification);
    }

    private void showNotification(MyNotification myNotification){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_DECOUNT, myNotification.getDescount());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(myNotification.getTitle())
                .setContentText(myNotification.getDescription())
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
