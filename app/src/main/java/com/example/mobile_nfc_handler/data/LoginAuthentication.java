package com.example.mobile_nfc_handler.data;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginAuthentication {

    private DatabaseReference reference;
    private String dbPassword;
    private String dbUsername;
    private String dbEmail;

    public boolean login(String username, String password) throws IOException {
            this.reference = FirebaseDatabase.getInstance().getReference().child("users").child(username);

            dbPassword = this.reference.child("password").toString();
            dbEmail = this.reference.child("email").toString();

            System.out.println("password server: " + dbPassword);
            System.out.println("email server: " + dbEmail);
            System.out.println("password input: " + password);
            /*
            this.reference.addValueEventListener( new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dbPassword = snapshot.child("password").getValue().toString();
                    dbEmail = snapshot.child("email").getValue().toString();

                    System.out.println("password server: " + dbPassword);
                    System.out.println("email server: " + dbEmail);
                    System.out.println("password input: " + password);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //Empty for now
                }
            });

             */
       try {
           return validatePassword(password, dbPassword);
       }catch(NullPointerException n ){
           System.out.println("Login error");
       }
       return false;

    }

    private boolean validatePassword(String inputPassword, String passwordDb){
        return inputPassword.compareTo(passwordDb) == 0;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}