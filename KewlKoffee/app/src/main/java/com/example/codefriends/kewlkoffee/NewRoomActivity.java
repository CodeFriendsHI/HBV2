package com.example.codefriends.kewlkoffee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author geirgardarsson
 *
 * Last updated by geirgardarsson on 3.4.2018
 */

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
        submitButton.setOnClickListener((View v) -> {
            if (!streamName.isEmpty()) {
                System.out.println(streamName);
                System.out.println(token);

                Call call = RoomsControl.mRoomservice.createRoom(streamName, "stream", token);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println("=================PRINTING RESPONSE===================");
                        try {
                            int newRoomId = Integer.parseInt(response.body().string());

                            Intent data = new Intent();
                            String text = "Adding room";

                            data.putExtra("roomId", newRoomId);

                            data.setData(Uri.parse(text));
                            setResult(RESULT_OK, data);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        finish();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });
    }

}
