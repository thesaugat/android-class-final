package com.thesaugat.androidclass.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.thesaugat.androidclass.Constants;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(Constants.NOTIFY_TEXT);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
}
