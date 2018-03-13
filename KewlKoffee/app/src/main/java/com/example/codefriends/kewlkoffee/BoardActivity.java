package com.example.codefriends.kewlkoffee;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

public class BoardActivity extends AppCompatActivity {


    private Button switchButton;
    private Button cameraButton;
    private RoomsControl room;
    private Button[] boards = new Button[20];

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
    }
}
