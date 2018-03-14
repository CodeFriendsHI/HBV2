package com.example.codefriends.kewlkoffee;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by adalsteinn95 on 13.3.2018.
 */

public interface RoomService {



    @POST("/rooms")
    @FormUrlEncoded
    Call<Void> createRoom(@Field("name") String name,
                        @Field("stream") String stream,
                        @Field("token") String token);


}
