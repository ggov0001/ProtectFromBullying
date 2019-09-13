package com.example.protectfrombullying;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

@SuppressLint("NewApi")
public class MyNotificationListenerService extends NotificationListenerService {

    private Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        String pack = statusBarNotification.getPackageName();
        String ticker = (String) statusBarNotification.getNotification().tickerText;
        Bundle extras = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
        {
            extras = statusBarNotification.getNotification().extras;
            String title = extras.getString("android.title");
            String text = extras.getCharSequence("android.text").toString();

            Log.i("Package",pack);
            //Log.i("Ticker",ticker);
            Log.i("Title",title);
            Log.i("Text",text);

            Intent msgrcv = new Intent();
            msgrcv.setAction("com.example.protectfrombullying");
            msgrcv.putExtra("package", pack);
            msgrcv.putExtra("ticker", ticker);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);
            sendBroadcast(msgrcv);
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        Log.i("Msg","Notification Removed");
    }


}
