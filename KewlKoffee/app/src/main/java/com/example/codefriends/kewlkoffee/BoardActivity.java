package com.example.codefriends.kewlkoffee;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import com.firebase.client.Firebase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
* Roomservice 
*
* @author  Aðalsteinn Ingi Pálsson
*          Daníel Guðnason
*          Fannar Gauti Guðmundsson
*          Geir Garðarsson
*
* @version 0.01
* @since   18.2.2018 
*/

public class BoardActivity extends AppCompatActivity {

    private List<Room> rooms;
    private Button notifactionButton;
    private RoomsControl room;
    private int NEW_ROOM_CODE = 1;
    private int STREAM_CODE = 2;
    private int STREAM_EXIT_CODE = 3;


    private void loadRooms(List<Room> r) {
        LinearLayout layout = findViewById(R.id.mainLayout);
        layout.removeViews(3,layout.getChildCount() - 3);
        TextView textView = new TextView(this);
        textView.setText("Board of streams");
        textView.setTextSize(24);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        for (int i = 0; i < r.size(); i++) {

            Room room = r.get(i);

            String roomName = room.getName();
            int roomId = room.getId();
            room.setStream(roomId);

            Button buttonItem = new Button(this);
            buttonItem.setText(roomName);
            buttonItem.setTextColor(getResources().getColor(android.R.color.white));
            buttonItem.setBackgroundResource(R.drawable.cup_coffee);
            int finalI = i;
            List<Room> finalR = r;
            buttonItem.setOnClickListener((View v) -> {
                StreamActivity.r = finalR.get(finalI);
                Intent intent = StreamActivity.newIntent(BoardActivity.this);
                startActivityForResult(intent, STREAM_CODE);
            });
            layout.addView(buttonItem, p);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        room = new RoomsControl();

        List<Room> r;
        r = room.getRooms();

        requestRooms();

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


        if (rooms != null) {
            loadRooms(r);
        }

        FloatingActionButton newroom = findViewById(R.id.newRoomButton);
        newroom.setOnClickListener(v -> {
            Intent intent = NewRoomActivity.newIntent(BoardActivity.this);
            startActivityForResult(intent, NEW_ROOM_CODE);
        });

        FloatingActionButton reload = findViewById(R.id.reloadButton);
        reload.setOnClickListener(v -> requestRooms());
    }


    private void requestRooms() {
        Call call = RoomsControl.mRoomservice.getRooms();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                rooms = (List<Room>) response.body();
                loadRooms(rooms);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed to get rooms");
            }
        });
        //set firebase context
        Firebase.setAndroidContext(this);

        notifactionButton = findViewById(R.id.buttonNotify);
        notifactionButton.setOnClickListener(v -> com.example.codefriends.kewlkoffee.Notification.wantKoffeeAll());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_ROOM_CODE) {
            if (resultCode == RESULT_OK) {
                int newRoomId = data.getExtras().getInt("roomId");


                Intent streamStart = ImageCaptureCamera2API.newIntent(BoardActivity.this);
                System.out.println("printing new room id");
                System.out.println(newRoomId);
                streamStart.putExtra("roomId", newRoomId);
                // streamStart.putExtra("roomObject", roomObject);

                startActivityForResult(streamStart,STREAM_EXIT_CODE);
            }
        }

        if (requestCode == STREAM_EXIT_CODE) {
            if (resultCode == RESULT_OK) {
                int roomId = data.getExtras().getInt("roomId");
                Call call = RoomsControl.mRoomservice.deleteRoom(roomId);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        System.out.println("Room was deleted");
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        }
    }
}