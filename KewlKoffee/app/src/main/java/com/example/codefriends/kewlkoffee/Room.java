package com.example.codefriends.kewlkoffee;

/**
 * Created by adalsteinn95 on 18.2.2018.
 *
 * Edited by geirgardarsson on 3.4.2018
 */

public class Room {

    private String name;
    private int id;
    private String token;
    private String stream;


    public Room(int id, String name, String stream, String token){
        this.id = id;
        this.name = name;
        this.stream = stream;
        this.token = token;
    }

    public String generateToken(){
        return "kewl";
    }

    public String getStream(){
        return stream;
    }

    public String getName(){
        return name;
    }
}
