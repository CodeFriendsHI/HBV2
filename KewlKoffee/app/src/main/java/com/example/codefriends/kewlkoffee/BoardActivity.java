package com.example.codefriends.kewlkoffee;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BoardActivity extends AppCompatActivity {


    private Button switchButton;
    private Button cameraButton;
    private Button newRoom;
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
                    System.out.print(response.body().string());
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

            for(int i = 0; i < r.size();i++){
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
            Call call = RoomsControl.mRoomservice.createRoom("la", "la", "lalala");

            call.enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) {
                    System.out.println("yay!");

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    System.out.println("oh no!");
                }
            });
        });
    }
}
