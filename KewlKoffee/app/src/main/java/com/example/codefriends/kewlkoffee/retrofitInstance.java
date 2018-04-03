package com.example.codefriends.kewlkoffee;
import android.content.Context;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
* RetroFit er kewl
*
* @author  Fannar Guðmundsson
* @version 0.01
* @since   22.2.2018 
*/

public class retrofitInstance {
    private static Retrofit retrofit;
    private static String BASE_URL;

    public static Retrofit getRetroInstance(Context context) {
        if (retrofit == null) {
            //BASE_URL = "http://10.0.2.2:3001/";
            BASE_URL = "https://kewlserver.herokuapp.com/";


            /*
            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

            OkHttpClient client = new OkHttpClient.Builder()
                    .cookieJar(cookieJar)
                    .build();

            */

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
