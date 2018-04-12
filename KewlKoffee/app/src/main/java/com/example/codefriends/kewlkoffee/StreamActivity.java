package com.example.codefriends.kewlkoffee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


 /**
* StreamActivity
*
* @author  Aðalsteinn Ingi Pálsson, Daníel Guðnason and Geir Gardarson
* @version 0.02
* @since   18.2.2018 
*/

public class StreamActivity extends AppCompatActivity {

    public static Room r;

    ProgressDialog dialog;
    ImageView imageView;
    ImageButton btnPlayPause;
    TextView nameView;
    private Button signupButton;
    private Button makingKoffeeButton;
    private Button wantKoffeeButton;
    private Button koffeereadyButton;


    String streamUrl = r.getStream();
    String imageUrl;



    public static Intent newIntent (Context packageContext) {
        Intent intent = new Intent(packageContext, StreamActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameView = findViewById(R.id.headerView);

        //Previous versions of Firebase
        Firebase.setAndroidContext(this);


        Handler handler = new Handler();
        int delay = 3000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url(streamUrl).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            imageUrl = response.body().string();
                        }
                    }
                });
                nameView.setText(r.getName());
                //Log.d("herbergi", "heitir " + r.getName());
                //btnPlayPause = findViewById(R.id.btn_play_pause);
                //btnPlayPause.setOnClickListener(view -> Picasso.with(getApplicationContext()).load(imageUrl).into(imageView));
                imageView = findViewById(R.id.imageView);
                Picasso.with(getApplicationContext()).load(imageUrl).into(imageView);
                handler.postDelayed(this, delay);
            }
        }, delay);

        /**
         * Skráir notenda við topic
         * Núna fær notandi skilaboð fyrir þetta herbergi
         */
        signupButton = findViewById(R.id.buttonSignup);
        signupButton.setOnClickListener(v -> {

            String name = r.getName().replaceAll("\\s+","-");
            Log.d("topicname" , name);

            FirebaseMessaging.getInstance().subscribeToTopic(name);

        });

        wantKoffeeButton = findViewById(R.id.wantKoffee);
        wantKoffeeButton.setOnClickListener(v -> Notification.wantKoffee(r));

        makingKoffeeButton = findViewById(R.id.makingKoffee);
        makingKoffeeButton.setOnClickListener(v -> Notification.makingKoffee(r));

        koffeereadyButton = findViewById(R.id.koffeeReady);
        koffeereadyButton.setOnClickListener(v -> Notification.koffeeReady(r));





    }



}
