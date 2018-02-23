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
 * Created by fannargudmundsson on 22/02/2018.
 */

public interface imageService{

    @FormUrlEncoded
    @POST("/post")
    Call <Void> postImage(@Field("image") String base);

    @GET("/{id}")
    Call <ResponseBody> getStuff(@Path("id") int id);


    /*
    @GET("images/{id}")
    Call<List<Image>> listRepos(@Path("id") String id);
    */

    /*
    retrofitInstance retrofit = new retrofitInstance();

    Retrofit retrofitService = retrofit.getRetroInstance(getApplicationContext());

    imageService service = retrofit.create(imageService.class);
    */






}
