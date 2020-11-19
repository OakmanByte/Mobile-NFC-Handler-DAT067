package com.example.mobile_nfc_handler.data.model;

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
     * @param eMail
     * @param password
     * @param admin
     */
    public User (String userName, String eMail, String password, boolean admin){
        this.userName = userName;
        this.email = eMail;
        this.password = password;
        this.admin = admin;
    }

}
