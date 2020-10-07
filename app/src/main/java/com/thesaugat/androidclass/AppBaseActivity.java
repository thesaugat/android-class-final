package com.thesaugat.androidclass;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class AppBaseActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        notificationchannel();
    }

    private void notificationchannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name =  Constants.DEFAULT_CHANNEL;
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel( Constants.DEFAULT_CHANNEL, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.d("Notification", "Channel Created");



            name =  Constants.CHANNEL_ONE;
             description = getString(R.string.channel_description_one);
             importance = NotificationManager.IMPORTANCE_HIGH;
             channel = new NotificationChannel( Constants.CHANNEL_ONE, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
             notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.d("Notification", "Channel One Created");
        }else {


        }
    }
}
