package com.thesaugat.androidclass.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.thesaugat.androidclass.Constants;
import com.thesaugat.androidclass.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                loginIntent.putExtra("Title","dfhasdhfasd");
                startActivity(loginIntent);
                finish();
            }
        }, 3000);

//        notificationchannel();

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
        }else {


        }
    }
}