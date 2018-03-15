package com.example.codefriends.kewlkoffee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewRoomActivity extends AppCompatActivity {

    private Button tokenButton;
    private Button submitButton;
    private TextView tokenTextView;
    private TextView streamTextView;
    static String streamName = "";
    static String token = "";

    public static Intent newIntent (Context packageContext) {
        Intent intent = new Intent(packageContext, NewRoomActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newroom);

        streamTextView = findViewById(R.id.streamName);
        streamTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                streamName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tokenButton = findViewById(R.id.tokenGen);
        tokenButton.setOnClickListener(v -> {
            token = RoomsControl.generateToken();

            tokenTextView = findViewById(R.id.tokenDisplay);
            tokenTextView.setText(token);
        });

        submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(v -> {
            if (streamName != "" && token != "") {
                System.out.println("=======YAY=======");
                System.out.println(streamName);
            }
        });
    }

}
