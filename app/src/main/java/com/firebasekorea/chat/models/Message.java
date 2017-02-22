package com.firebasekorea.chat.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by namhoonkim on 17/02/2017.
 */

public class Message {
    public String userName;
    public String message;
    public Boolean isDeleted;


    public Message() {
        this.isDeleted = false;
    }

    public Message(String userName, String message) {
        this.userName = userName;
        this.message = message;
        this.isDeleted = false;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("message", message);
        result.put("isDeleted", isDeleted);
        return result;
    }

    @Exclude
    public static Message parseSnapshot(DataSnapshot snapshot) {
        Message msg = new Message();
        msg.userName = (String) snapshot.child("userName").getValue();
        msg.message = (String) snapshot.child("message").getValue();
        msg.isDeleted = (Boolean) snapshot.child("isDeleted").getValue();
        return msg;
    }
}
