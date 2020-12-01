package com.example.mobile_nfc_handler.tools;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class DatabaseInformation {

   static private FirebaseAuth mAuth;
   static private FirebaseDatabase db;


    public DatabaseInformation() {

        this.mAuth = FirebaseAuth.getInstance();
        // Get our instance of the database
        this.db = FirebaseDatabase.getInstance();
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static FirebaseDatabase getDb() {
        return db;
    }
}
