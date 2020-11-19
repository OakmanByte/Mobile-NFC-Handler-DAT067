package com.example.mobile_nfc_handler.data;

import androidx.annotation.NonNull;

import com.example.mobile_nfc_handler.generic.Result;
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

    public Result<User> login(String username, String password) {
            this.reference = FirebaseDatabase.getInstance().getReference().child("users").child(username);
            this.reference.addValueEventListener( new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dbPassword = snapshot.child("password").getValue().toString();
                    dbEmail = snapshot.child("email").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //Empty for now
                }
            });

        try {
            // Kolla om rätt lösenord
            if ( dbPassword.equals(password)) {
                User user =  new User(username, dbEmail, password, false);

                return new Result.Success<>(user);
            }

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
        return null;

    }

    public void logout() {
        // TODO: revoke authentication
    }
}