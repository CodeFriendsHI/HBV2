package com.example.codefriends.kewlkoffee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.stream.Stream;

public class BoardActivity extends AppCompatActivity {


    private Button switchButton;
    private Button cameraButton;

    public static Intent newIntent (Context packageContext) {
        Intent intent = new Intent(packageContext, StreamActivity.class);

        return intent;
    }

    public static Intent newCamera (Context packageContext) {
        Intent intent = new Intent(packageContext, ImageCaptureCamera2API.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        switchButton = findViewById(R.id.button);
        cameraButton = findViewById(R.id.button2);

        switchButton.setOnClickListener (new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.d("Hello","WARLOCK");
                // Start CheatActivity
                //   Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                Intent intent = StreamActivity.newIntent(BoardActivity.this);
                //            startActivity(intent);
                // Starting an activity and hoping to get result
                startActivityForResult(intent, 0);
                }});
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
