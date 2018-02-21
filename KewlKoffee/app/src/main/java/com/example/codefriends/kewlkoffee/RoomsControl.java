package com.example.codefriends.kewlkoffee;

/**
 * Created by adalsteinn95 on 18.2.2018.
 */

public class RoomsControl {

    public Rooms[] rooms =  new Rooms[20];


    public RoomsControl(){

        for (int i = 0; i < 20;i++) {
            rooms[i] = new Rooms(i, "la", "la", "https://www.althingi.is/myndir/mynd/thingmenn/162/org/mynd.jpg");
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
