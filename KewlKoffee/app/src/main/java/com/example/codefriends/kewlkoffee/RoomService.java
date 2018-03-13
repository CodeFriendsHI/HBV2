package com.example.codefriends.kewlkoffee;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by adalsteinn95 on 13.3.2018.
 */

public interface RoomService {



    @GET("/streams/{id}")
    Call <ResponseBody> getStuff(@Path("id") int id);


}
