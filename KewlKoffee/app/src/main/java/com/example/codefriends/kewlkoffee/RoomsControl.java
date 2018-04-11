package com.example.codefriends.kewlkoffee;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Retrofit;


/**
* Roomscontrol manages the rooms 
*
* @author  Aðalsteinn Ingi Pálsson
* @version 0.01
* @since   18.2.2018 
*/

public class RoomsControl {

    public List<Room> rooms = new ArrayList<>(20);

    public static RoomService mRoomservice;

    private Retrofit retrofit;



    public RoomsControl(){

        for (int i = 0; i < 5;i++) {
            rooms.add(new Room(i, "Stream " + i, "https://kewlserver.herokuapp.com/streams/" + i, "lala"));
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
    public List<Room> getRooms(){

        return rooms;
    }

    /**
     *
     * Add a stream
     * 
     * @param name - name of the stream
     * @param admin - name of the owner
     * @param url - streamUrl
     *
     */


    public void createRoom(String name, String admin, String url){
        this.rooms.add(new Room(rooms.size() + 1, name, admin, url));

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
    public Optional<Room> findRooms(List<Room> rooms, final String search){
        return rooms.stream()
                .filter(i -> i.getName().equals(search)).findAny();
    }


    public static void main(String[] args) {
        RoomsControl a = new RoomsControl();


    }

    public static String generateToken() {

        String[] words = {"cool", "java", "spring", "koffee", "time", "react", "wow", "beans"};

        int a = (int)Math.floor(Math.random()*words.length);
        int b = (int)Math.floor(Math.random()*words.length);
        int c = (int)Math.floor(Math.random()*words.length);

        String token = words[a] + " " + words[b] + " " + words[c];

        return token;
    }

}
