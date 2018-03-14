package com.example.codefriends.kewlkoffee;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;

public class BoardActivity extends AppCompatActivity {


    private Button switchButton;
    private Button cameraButton;
    private Button notifactionButton;
    private RoomsControl room;
    private Button[] boards = new Button[20];
    Context context = this;

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

        List<Rooms> r;
        r = room.getRooms();

        //Optional<Rooms> test = room.findRooms(r ,"lala");
        //final Rooms rooms = test.get();

        //System.out.print(rooms.getName());


        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
            TextView textView = new TextView(this);
            textView.setText("Board of streams");
            textView.setTextSize(24);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            layout.addView(textView, p);

            for(int i = 0; i < 5;i++){
                Button buttonItem = new Button(this);
                buttonItem.setText("Stream " + i);
                buttonItem.setBackgroundResource(R.drawable.ic_test);
                int finalI = i;
                List<Rooms> finalR = r;
                buttonItem.setOnClickListener ((View v) -> {
                    StreamActivity.r = finalR.get(finalI);
                    Intent intent = StreamActivity.newIntent(BoardActivity.this);
                    startActivityForResult(intent, 0);
                });
                layout.addView(buttonItem, p);
            }


        cameraButton = findViewById(R.id.button2);
        cameraButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
                //   Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                Intent intent = ImageCaptureCamera2API.newIntent(BoardActivity.this);
                //            startActivity(intent);
                // Starting an activity and hoping to get result
                startActivityForResult(intent, 0);
            }});

        notifactionButton = findViewById(R.id.buttonNotify);
        notifactionButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tittle="Halló";
                String subject="Geir";
                String body="gang$sta";

                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                        setContentTitle(subject).setSmallIcon(R.drawable.ic_launcher_background).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
                //notifytest();
                //usethis();
                //createNotification(v);
           // }});
    }});
/*

    public void createNotification(View view) {
        System.out.println("ping ping ping ping ping ping ping ping ping pingpin ping ping");
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, Notification.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_launcher_background, "Call", pIntent)
                .addAction(R.drawable.ic_launcher_background, "More", pIntent)
                .addAction(R.drawable.ic_launcher_background, "And more", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }


    //IGNORE EYÐA ÁÐUR EN PUSHAÐ

    public void notifytest () {
        System.out.println("ping ping ping ping ping ping ping ping ping pingpin ping ping");
        //TODO:
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        //Intent notificationIntent = new Intent(this, BoardActivity.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
        //        PendingIntent.FLAG_UPDATE_CURRENT);
        //builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        System.out.println(manager);
        System.out.println("check");
        /*NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        //Intent notificationIntent = new Intent(this, MainA.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
        //        PendingIntent.FLAG_UPDATE_CURRENT);
        //builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());

/*
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!");


        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());*/
    }

    public void usethis(){
       String tittle="Halló";
            String subject="Geir";
            String body="gang$sta";

            NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification.Builder
               (getApplicationContext()).setContentTitle(tittle).setContentText(body).
               setContentTitle(subject).setSmallIcon(R.drawable.ic_launcher_background).build();

               notify.flags |= Notification.FLAG_AUTO_CANCEL;
               notif.notify(0, notify);
    }
}

