package com.example.codefriends.kewlkoffee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.stream.Stream;

public class BoardActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.codefriends.kewlkoffee.answer_is_true";

    private static final String EXTRA_ANSWER_SHOWN =
            "com.example.codefriends.kewlkoffee.answer_shown";

    private Button switchButton;

    public static Intent newIntent (Context packageContext) {
        Intent intent = new Intent(packageContext, StreamActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, true);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        switchButton = findViewById(R.id.button);
        switchButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
                switchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                        Intent intent = StreamActivity.newIntent(BoardActivity.this);
                        //            startActivity(intent);
                        // Starting an activity and hoping to get result
                        startActivityForResult(intent, 0);

                    }
                });}});
    }
}
