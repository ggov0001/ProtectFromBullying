package com.example.protectfrombullying;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
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

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
        {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("timeStampDetails", MODE_PRIVATE);
            SharedPreferences.Editor editTimeStamp = sharedPreferences.edit();

            //Allow to proceed only if the time stamp of the current notifications doesnt match with previous
            //To avoid repeatation of same notification being captured
            if(! String.valueOf(statusBarNotification.getNotification().when).equals(sharedPreferences.getString("timeStampOfPreviousNotification","")))
            {
                String pack = statusBarNotification.getPackageName();
                String ticker = (String) statusBarNotification.getNotification().tickerText;
                Bundle extras = null;
                extras = statusBarNotification.getNotification().extras;
                String title = extras.getString("android.title");
                String text = extras.getCharSequence("android.text").toString();

                editTimeStamp.putString("timeStampOfPreviousNotification", String.valueOf(statusBarNotification.getNotification().when));
                editTimeStamp.apply();

                Log.i("SBN Time stamp:", String.valueOf(statusBarNotification.getNotification().when));
                Log.i("Package", pack);
                //Log.i("Ticker",ticker);
                Log.i("Title", title);
                Log.i("Text", text);

                if (pack.equals("com.whatsapp")) {
                    if ((ticker == null)) {
                        Intent msgrcv = new Intent();
                        msgrcv.setAction("com.example.protectfrombullying");
                        msgrcv.putExtra("package", pack);
                        msgrcv.putExtra("ticker", ticker);
                        msgrcv.putExtra("title", title);
                        msgrcv.putExtra("text", text);
                        sendBroadcast(msgrcv);
                    }
                } else {
                    if (!(ticker == null)) {
                        if (!title.equals("Chat heads active") && !title.equals("Messenger is displaying over other apps")) {
                            Intent msgrcv = new Intent();
                            msgrcv.setAction("com.example.protectfrombullying");
                            msgrcv.putExtra("package", pack);
                            msgrcv.putExtra("ticker", ticker);
                            msgrcv.putExtra("title", title);
                            msgrcv.putExtra("text", text);
                            sendBroadcast(msgrcv);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        Log.i("Msg","Notification Removed");
    }


}
