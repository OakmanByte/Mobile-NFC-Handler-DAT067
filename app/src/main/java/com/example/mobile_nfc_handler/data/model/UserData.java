package com.example.mobile_nfc_handler.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserData {

    // Det är public för att Firebase vill ha det så
    public String userId;
    public Map<String, String> cards;

    public UserData (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserData(User user, HashMap cards ){
        this.userId = user.userName;
        this.cards = cards;
    }

}
