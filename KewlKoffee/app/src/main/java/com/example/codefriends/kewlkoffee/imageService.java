package com.example.codefriends.kewlkoffee;

import android.media.Image;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


/**
* ImageService
*
* @author  Fannar Guðmundsson
* @version 0.01
* @since   22.2.2018 
*/

public interface imageService{

    @FormUrlEncoded
    @POST("/streams")
    Call <Void> postImage(@Field("image") String base, @Field("roomId") int id);

    @GET("/{id}")
    Call <ResponseBody> getStuff(@Path("id") int id);


}
