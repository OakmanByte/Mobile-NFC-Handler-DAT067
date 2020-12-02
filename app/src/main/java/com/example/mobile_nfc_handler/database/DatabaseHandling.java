package com.example.mobile_nfc_handler.database;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.mobile_nfc_handler.data.NFCData;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.data.UserData;
import com.example.mobile_nfc_handler.main.StartingScreenActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandling<T> {

    public static void writeNFCToDataBase(FirebaseDatabase db, String uid, String location, NFCData data){
        //TODO
    }

    public static void getUserFromDatabase(String uid){
        DatabaseInformation.getDb().getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StartingScreenActivity.theUser = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getUserDataFromDatabase(String uid){
        DatabaseInformation.getDb().getReference().child("userData").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue(UserData.class) == null) {

                    System.out.println("AM HERE BOU");
                    //Creates a standard userData for new users such that userData isn't uninitilized or null/empty
                    UserData currentUser = new UserData(StartingScreenActivity.theUser, new ArrayList<NFCData>());
                    StartingScreenActivity.theUsersData = currentUser;

                    DatabaseInformation.getDb().getReference().child("userData").child(uid).setValue(StartingScreenActivity.theUsersData);

                }
                else {

                    StartingScreenActivity.theUsersData = snapshot.getValue(UserData.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
