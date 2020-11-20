package com.example.mobile_nfc_handler.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class User {

    // Det är public för att Firebase vill ha det så
    public String userName;
    public String email;
    public String password;
    public boolean admin;


    public User (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    /**
     *
     * @param userName
     * @param email
     * @param password
     * @param admin
     */
    public User (String userName, String email, String password, boolean admin){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
}
