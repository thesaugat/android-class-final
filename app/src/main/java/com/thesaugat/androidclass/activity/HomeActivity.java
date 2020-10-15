package com.thesaugat.androidclass.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thesaugat.androidclass.Constants;
import com.thesaugat.androidclass.R;
import com.thesaugat.androidclass.SimpleLocation;
import com.thesaugat.androidclass.adapters.NewsFeedAdapter;
import com.thesaugat.androidclass.dataClases.FeedData;
import com.thesaugat.androidclass.dataClases.MessageData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements SimpleLocation.Listener {
    Button btnTwo, btnOne;
    TextView locationView;
    RecyclerView newsFeedRV;
    EditText title, body;
    SimpleLocation simpleLocation;
    String t, b;
    public static List<MessageData> messageDataList = new ArrayList<>();

    MediaSession mediaSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title = findViewById(R.id.notificationTitleET);
        body = findViewById(R.id.notificationBodyEt);
        btnTwo = findViewById(R.id.btnNotifyTwo);
        btnOne = findViewById(R.id.btnNotifyOne);
        newsFeedRV = findViewById(R.id.newsFeedRV);
        locationView = findViewById(R.id.myLocationTV);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setNotificationTwo();
                setNotificationTwoAlt();

            }
        });
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotificationOne();
            }
        });

        newsFeedRV.setHasFixedSize(true);
        newsFeedRV.setLayoutManager(new LinearLayoutManager(this));
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(getFeedDataList(), this);
        newsFeedRV.setAdapter(newsFeedAdapter);
        mediaSession = new MediaSession(this, "tag");


    }

    private void setNotificationOne() {
        t = title.getText().toString();
        b = body.getText().toString();

//        Intent intent = new Intent(this, NotificationDataActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Input field for message

        RemoteInput remoteInput = new RemoteInput.Builder("reply_key")
                .setLabel("Type Here to reply...")
                .build();


        Intent replyIntent = new Intent(this, MessageReceiver.class);
        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(this, 101, replyIntent, 0);

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(R.drawable.ic_reply_message, "Reply", replyPendingIntent)
                .addRemoteInput(remoteInput).build();

        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle("Ram");
        messagingStyle.setConversationTitle("BCA 2016 Group");
        messageDataList.add(new MessageData("Hello!", "Ram"));
        messageDataList.add(new MessageData("Hi!", "Shyam"));
        messageDataList.add(new MessageData("K cha!", "Hari"));
        messageDataList.add(new MessageData("Exam Ayoooo!", "Sunil"));
        messageDataList.add(new MessageData("Hello!", "Kabita"));
        messageDataList.add(new MessageData("Hello!", "Suruchi"));

        for (MessageData chatData : messageDataList) {
            NotificationCompat.MessagingStyle.Message notificationMessage = new NotificationCompat.MessagingStyle.Message(
                    chatData.getText(),
                    chatData.getTimeStamp(),
                    chatData.getSender()
            );
            messagingStyle.addMessage((notificationMessage));

        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, Constants.CHANNEL_ONE);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setStyle(messagingStyle);
        notification.addAction(replyAction);

        notification.setCategory(NotificationCompat.CATEGORY_MESSAGE);
        notification.setPriority(NotificationCompat.PRIORITY_HIGH);
        notification.setAutoCancel(false);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification.build());


    }


    private void setNotificationTwo() {
        t = title.getText().toString();
        b = body.getText().toString();

        // this is the pending intent.
        Intent intent = new Intent(this, NotificationDataActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.boy_placeholder);
        MediaSessionCompat mediaSession = new MediaSessionCompat(getApplicationContext(), "session tag");

        MediaSessionCompat.Token token = mediaSession.getSessionToken();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Constants.DEFAULT_CHANNEL)
                .setSmallIcon(R.drawable.ic_facebook)
                .setContentTitle(t)
                .setContentText(b)
                .setLargeIcon(largeIcon)
                .addAction(R.drawable.ic_dislike, "Dislike", null)
                .addAction(R.drawable.ic_previous, "Previous", null)
                .addAction(R.drawable.ic_pause, "Pause", null)
                .addAction(R.drawable.ic_next, "Next", null)
                .addAction(R.drawable.ic_like, "Like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(token)
                )
                .setSubText("This is action text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        Log.d("Notification", "Notification ");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    private void setNotificationTwoAlt() {
        t = "My Proposal.docx ";
        b = "Download is in progress";

        final int progressMax = 100;


        final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, Constants.DEFAULT_CHANNEL)
                .setSmallIcon(R.drawable.ic_facebook)
                .setContentTitle(t)
                .setContentText(b)
                .setProgress(progressMax, 0, false)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);


        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for (int progress = 0; progress <= progressMax; progress += 10) {
                    notification.setProgress(progressMax, progress, false);
                    notificationManager.notify(2, notification.build());
                    SystemClock.sleep(1000);

                }
                notification.setContentTitle("Download Finished!")
                        .setOnlyAlertOnce(true)
                        .setProgress(0, 0, false);
                notificationManager.notify(2, notification.build());

            }
        }).start();
    }

    private List<FeedData> getFeedDataList() {

        List<FeedData> feedDataArrayList = new ArrayList<>();

        FeedData feedData = new FeedData();
        feedData.setImageUrl("https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60");
        feedData.setPersonName("Shyam Gurung");
        feedData.setDateTime("10 hours ago");
        feedData.setDescription("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)");
        feedData.setLikeCount(100);
        feedData.setLikeData(null);

        FeedData feedData1 = new FeedData();
        feedData1.setPersonName("Ram Adhikari");
        feedData1.setImageUrl("https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60");
        feedData1.setDateTime("5 hours ago");
        feedData1.setDescription("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");
        feedData1.setLikeCount(100);
        feedData1.setLikeData(null);

        FeedData feedData3 = new FeedData();
        feedData3.setPersonName("Jenifer Jhonson");
        feedData3.setDateTime("10 minutes ago");
        feedData3.setDescription("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance.");
        feedData3.setLikeCount(100);
        feedData3.setLikeData(null);
        feedData3.setImageUrl("https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60");

        FeedData feedData4 = new FeedData();
        feedData4.setPersonName("Jessie Jhonson");
        feedData4.setDateTime("19 minutes ago");
        feedData4.setDescription("Random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance.");
        feedData4.setLikeCount(100);
        feedData4.setLikeData(null);
        feedData4.setImageUrl("https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60");


        feedDataArrayList.add(feedData);
        feedDataArrayList.add(feedData1);
        feedDataArrayList.add(feedData3);
        feedDataArrayList.add(feedData4);

        return feedDataArrayList;
    }


    Boolean getLocationPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    void afterLocationPermissionGranted() {
        Toast.makeText(HomeActivity.this, "Pemission IS Granted!!!", Toast.LENGTH_SHORT).show();


//        simpleLocation = new SimpleLocation(this);
//        simpleLocation.setListener(this);
//        simpleLocation.beginUpdates();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
//            Toast.makeText(HomeActivity.this, "On Pemission Result!!!", Toast.LENGTH_SHORT).show();
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                afterLocationPermissionGranted();
            }

        }
    }

    @Override
    public void onPositionChanged() {
        Address address = simpleLocation.getAddress();
        locationView.setText(address.getCountryName());
        simpleLocation.endUpdates();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}