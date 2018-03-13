package com.example.codefriends.kewlkoffee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Edited 13/3 by Geir Gardarsson
 */

public class StreamActivity extends AppCompatActivity {

    public static Rooms r;

    ProgressDialog dialog;
    ImageView imageView;
    ImageButton btnPlayPause;
    TextView nameView;

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

        imageView = findViewById(R.id.imageView);
        nameView = findViewById(R.id.headerView);

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(streamUrl)
                .build();


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

        btnPlayPause = findViewById(R.id.btn_play_pause);
        btnPlayPause.setOnClickListener(view -> Picasso.with(getApplicationContext()).load(imageUrl).into(imageView));

    }

}
