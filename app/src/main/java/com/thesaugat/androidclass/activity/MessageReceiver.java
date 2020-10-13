package com.thesaugat.androidclass.activity;

import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.thesaugat.androidclass.dataClases.MessageData;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if(remoteInput != null){
            CharSequence replyText = remoteInput.getCharSequence("reply_key");
            MessageData messageData = new MessageData( replyText, null);
            HomeActivity.messageDataList.add(messageData);
        }
    }
}


