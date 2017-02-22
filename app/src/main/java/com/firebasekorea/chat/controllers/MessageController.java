package com.firebasekorea.chat.controllers;

import com.firebasekorea.chat.models.Message;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by namhoonkim on 17/02/2017.
 */

public class MessageController {

    public static String createMessage() {
        String messageKey = FirebaseDatabase.getInstance().getReference().child("messages").push().getKey();

        Message message = new Message();
        Map<String, Object> messageValue = message.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/messages/" + messageKey, messageValue);


        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);

        return messageKey;
    }

    public static void editMessage(String messageKey, Message message) {
        Map<String, Object> messageValue = message.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(messageKey, messageValue);

        FirebaseDatabase.getInstance().getReference().child("messages").updateChildren(childUpdates);
    }

    public static void updateMessage(String messageKey, String userName, String messageBody) {
        Message message = new Message();
        message.userName = userName;
        message.message = messageBody;

        editMessage(messageKey, message);
    }
}
