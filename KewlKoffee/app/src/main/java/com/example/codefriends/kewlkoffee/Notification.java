package com.example.codefriends.kewlkoffee;


import android.app.Activity;
import android.util.Log;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;


/**
* Notification 
*
* @author  Daniel Gudnason
* @version 0.01
* @since   14.3.2018
*/



public class Notification extends Activity {

    /**
     *
     * @param label string label for notifaction
     * @param message string message
     * @param topic string what group the notifications should be sent to
     *              sends the notification to our firebase server, our own server
     *              then gets it from the firebase server, composes the message and
     *              sends it to firebase. Firebase then sends the notifaction.
     */
    public static void sendNotificationToTopic(String label, final String message, String topic) {
        Firebase ref = new Firebase("https://kewlkoffee.firebaseio.com/");
        final Firebase notifications = ref.child("notificationRequests");

        Map notification = new HashMap<>();
        notification.put("label", label);
        notification.put("message", message);
        notification.put("topic", topic);

        notifications.push().setValue(notification);
    }

    static void makingKoffee(Room r){
        String label = "Í vinnslu";
        String message = "Verið er að hella upp á kaffi";
        String topic = r.getName().replaceAll("\\s+","-");
        Notification.sendNotificationToTopic(label, message, topic );
    }

    static void makingKoffeeName(String r){
        String label = "Ready";
        String message = "Kaffið er tilbúið";
        String topic = r.replaceAll("\\s+","-");
        Notification.sendNotificationToTopic(label, message, topic );
    }

    static void koffeeReady(Room r){
        String label = "Tilbúið";
        String message = "Kaffið er tilbúið";
        String topic = r.getName().replaceAll("\\s+","-");
        Notification.sendNotificationToTopic(label, message, topic );
    }

    static void wantKoffee(Room r){
        String label = "Kaffi?";
        String message = "Getur einhver hellt upp á kaffi?";
        String topic = r.getName().replaceAll("\\s+","-");
        Notification.sendNotificationToTopic(label, message, topic );
    }

    static void wantKoffeeName(String r){
        String label = "Kaffi?";
        String message = "Getur einhver hellt upp á kaffi?";
        String topic = r.replaceAll("\\s+","-");
        Notification.sendNotificationToTopic(label, message, topic );
    }

    static void wantKoffeeAll(){
        Log.d("ping", "check");
        String label = "Kaffi?";
        String message = "Getur einhver hellt upp á kaffi?";
        Notification.sendNotificationToTopic(label, message, "all" );
        Log.d("ping", "check2");
    }

}
