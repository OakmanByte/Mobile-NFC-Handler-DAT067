package com.example.mobile_nfc_handler.tools;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.mobile_nfc_handler.data.NFCData;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.data.UserData;
import com.example.mobile_nfc_handler.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                    cards.add(" Card name:\t" + nfc.getName() + "\n Card ID:\t" + nfc.getNfcID());
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
                LoginActivity.theUser = snapshot.getValue(User.class);
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
                LoginActivity.theUsersData = snapshot.getValue(UserData.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
