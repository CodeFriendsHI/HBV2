package com.example.codefriends.kewlkoffee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class StreamActivity extends AppCompatActivity implements  View.OnClickListener {

    ProgressDialog dialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    String videoURL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public static Intent newIntent (Context packageContext) {
        Intent intent = new Intent(packageContext, StreamActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.videoView);
        btnPlayPause = (ImageButton)findViewById(R.id.btn_play_pause);
        btnPlayPause.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        dialog = new ProgressDialog(StreamActivity.this);
        dialog.setMessage("Please wait....");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        try {
            if(!videoView.isPlaying()){
                Uri uri = Uri.parse(videoURL);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btnPlayPause.setImageResource(R.drawable.ic_play);
                    }
                });
            } else {
                videoView.pause();
                btnPlayPause.setImageResource((R.drawable.ic_play));
            }
        }
        catch (Exception ex){

        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                dialog.dismiss();
                mediaPlayer.setLooping(true);

                videoView.start();
                btnPlayPause.setImageResource(R.drawable.ic_pause);

            }
        });

    }

}
