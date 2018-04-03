package com.example.codefriends.kewlkoffee;

import android.os.Bundle;
import android.app.Activity;

import com.firebase.client.Firebase;

/**
* Notification 
*
* @author  Daniel Gudnason
* @version 0.01
* @since   14.3.2018
*/



public class Notification extends Activity {
    Firebase myFirebaseRef = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com/");
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        // setContentView(R.layout.notifications);


    }
}
