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
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.List;

public class DatabaseHandling<T> {

    public static void readNFCDatabase(FirebaseDatabase db, List cards, ArrayAdapter adapter, String uid) {
        db.getReference().child("userData").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData data = snapshot.getValue(UserData.class);
                System.out.println("Tried to update card data in view");
                System.out.println("the snapshot: " + snapshot.toString());

                // Loops through the users cards and prints them out in the UI
                for (NFCData nfc : data.getCards()) {
                    System.out.println(nfc);
                    cards.add( "\n Card ID:\t" + nfc.getNfcID() + " Card name:\t" + nfc.getName() );
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Ignore
            }
        });
    }

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
                StartingScreenActivity.theUsersData = snapshot.getValue(UserData.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
