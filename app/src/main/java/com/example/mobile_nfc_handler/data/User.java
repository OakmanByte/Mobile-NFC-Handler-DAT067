package com.example.mobile_nfc_handler.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Generic User class for representing a registered User.
 */
@IgnoreExtraProperties
public class User {

    // Det är public för att Firebase vill ha det så
    public String userID;
    public String email;
    public boolean admin;


    public User (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    /**
     *
     * @param email
     * @param admin
     */
    public User (String id, String email, boolean admin){
        this.userID = id;
        this.email = email;
        this.admin = admin;
    }
}
