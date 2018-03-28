package com.example.codefriends.kewlkoffee;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
* Roomscontrol manages the rooms 
*
* @author  Aðalsteinn Ingi Pálsson
* @version 0.01
* @since   18.2.2018 
*/

public class RoomsControl {

    public List<Rooms> rooms = new ArrayList<>(20);

    public static RoomService mRoomservice;

    private Retrofit retrofit;



    public RoomsControl(){

        for (int i = 0; i < 5;i++) {
            rooms.add(new Rooms(i, "Stream " + i, "lala", "https://kewlserver.herokuapp.com/streams/" + i));
        }

        retrofit = retrofitInstance.getClient("https:/kewlserver.herokuapp.com/");
        mRoomservice = retrofit.create(RoomService.class);
    }


    /**
     *
     * get all the rooms via server
     * 
     * @return List of rooms
     *
     */
    public List<Rooms> getRooms(){
        return rooms;
    }

    /**
     *
     * Add a stream
     * 
     * @param name - name of the stream
     * @param Admin - name of the owner
     * @param stream - streamUrl  
     *
     */

    public void createRoom(String name, String admin, String stream){

        rooms.add(new Rooms(name,admin,stream));
    }

    /**
     *
     * Find rooms
     * 
     * @param search - search string
     * 
     * @return List of rooms
     *
     */

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.N)
    public Optional<Rooms> findRooms(final String search){
        return rooms.stream()
                .filter(i -> i.getName().equals(search)).findAny();
    }


    public static void main(String[] args) {
        RoomsControl a = new RoomsControl();


    }

}
