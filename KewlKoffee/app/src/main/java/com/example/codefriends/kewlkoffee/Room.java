package com.example.codefriends.kewlkoffee;

/**
* Room object
*
* @author  Aðalsteinn Ingi Pálsson
* @version 0.01
* @since   18.2.2018 
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

    public void setStream(int id) {
        String baseUrl = "https://kewlserver.herokuapp.com/";
        this.stream = baseUrl + Integer.toString(id);
    }

    public int getId() {
        return this.id;
    }
}
