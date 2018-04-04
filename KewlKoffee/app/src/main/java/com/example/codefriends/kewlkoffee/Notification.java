package com.example.codefriends.kewlkoffee;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

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

        Log.d("ping", "check");

        notifications.push().setValue(notification);
    }

}
