package com.example.codefriends.kewlkoffee;

/**
 * Created by adalsteinn95 on 18.2.2018.
 */

public class RoomsControl {

    public Rooms[] rooms =  new Rooms[5];


    public RoomsControl(){

        for (int i = 0; i < 5;i++) {
            rooms[i] = new Rooms(i, "Stream " + i, "la", "https:/kewlserver.herokuapp.com/" + i);
        }
    }


    /**
     *
     * get all the rooms via server
     *
     */
    public Rooms[] getRooms(){
        return rooms;
    }

    /**
     *
     * createRooms
     *
     */

    public void createRooms(){

    }

    /**
     *
     * Find rooms
     *
     */

    public void findRooms(){

    }


}
