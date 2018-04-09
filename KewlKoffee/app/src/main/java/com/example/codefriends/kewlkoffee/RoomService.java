package com.example.codefriends.kewlkoffee;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
* Roomservice 
*
* @author  Aðalsteinn Ingi Pálsson
* @version 0.01
* @since   13.3.2018 
*/
public interface RoomService {

    @POST("/rooms")
    @FormUrlEncoded
    Call<ResponseBody> createRoom(@Field("name") String name,
                                  @Field("stream") String stream,
                                  @Field("token") String token);

    @GET("/rooms")
    Call<List<Room>> getRooms();


    @DELETE("/rooms/{id}")
    Call<Void> deleteRoom(@Path("id") int id);
}
