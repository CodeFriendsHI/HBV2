package com.example.codefriends.kewlkoffee;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;


public class BoardActivity extends AppCompatActivity {


    private Button switchButton;
    private Button cameraButton;
    private Button newRoom;
    private Button notifactionButton;
    private RoomsControl room;
    private Button[] boards = new Button[20];
    Context context = this;
    private int NEW_ROOM_CODE = 1;
    private int STREAM_START_CODE = 2;

    public static Intent newIntent (Context packageContext) {
        Intent intent = new Intent(packageContext, StreamActivity.class);

        return intent;
    }

    public static Intent newCamera (Context packageContext) {
        Intent intent = new Intent(packageContext, ImageCaptureCamera2API.class);

        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        room = new RoomsControl();

        List<Room> r;
        r = room.getRooms();

        //Optional<Room> test = room.findRooms(r ,"lala");
        //final Room rooms = test.get();

        //System.out.print(rooms.getName());

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://kewlserver.herokuapp.com/rooms")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    //System.out.println(response.body().string());
                }
            }
        });

        LinearLayout layout = findViewById(R.id.mainLayout);
        TextView textView = new TextView(this);
        textView.setText("Board of streams");
        textView.setTextSize(24);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layout.addView(textView, p);

        for (int i = 0; i < 5; i++) {
            Button buttonItem = new Button(this);
            buttonItem.setText("Stream " + i);
            buttonItem.setBackgroundResource(R.drawable.ic_test);
            int finalI = i;
            List<Room> finalR = r;
            buttonItem.setOnClickListener((View v) -> {
                StreamActivity.r = finalR.get(finalI);
                Intent intent = StreamActivity.newIntent(BoardActivity.this);
                startActivityForResult(intent, 0);
            });
            layout.addView(buttonItem, p);
        }

        cameraButton = findViewById(R.id.button2);
        cameraButton.setOnClickListener (v -> {
            // Start CheatActivity
            //   Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
            Intent intent = ImageCaptureCamera2API.newIntent(BoardActivity.this);
            //            startActivity(intent);
            // Starting an activity and hoping to get result
            startActivityForResult(intent, 0);
        });

        newRoom = findViewById(R.id.newRoomButton);
        newRoom.setOnClickListener(v -> {
            Intent intent = NewRoomActivity.newIntent(BoardActivity.this);
            startActivityForResult(intent, NEW_ROOM_CODE);
        });



        notifactionButton = findViewById(R.id.buttonNotify);
        notifactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNotification("Það er til kaffi");

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_ROOM_CODE) {
            if (resultCode == RESULT_OK) {
                int newRoomId = data.getExtras().getInt("roomId");
                Intent streamStart = ImageCaptureCamera2API.newIntent(BoardActivity.this);
                streamStart.putExtra("roomId", newRoomId);
                startActivityForResult(streamStart,STREAM_START_CODE);
            }
        }
    }


    private NotificationManager notifManager;

    public void createNotification(String aMessage) {
        final int NOTIFY_ID = 1002;

        // There are hardcoding only for show it's just strings
        String name = "my_package_channel";
        String id = "my_package_channel_1"; // The user-visible name of the channel.
        String description = "my_package_first_channel"; // The user-visible description of the channel.

        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        if (notifManager == null) {
            notifManager =
                    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, id);

            intent = new Intent(this, BoardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(aMessage)  // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {

            builder = new NotificationCompat.Builder(this);

            intent = new Intent(this, BoardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(aMessage)                           // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        } // else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }
}

