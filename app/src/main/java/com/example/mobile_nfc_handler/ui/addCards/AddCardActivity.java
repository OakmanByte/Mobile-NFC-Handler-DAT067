package com.example.mobile_nfc_handler.ui.addCards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class AddCardActivity extends AppCompatActivity implements UISetup {

    private Button addCardReturnButton;
    private Button addCardInActivityButton;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);



        this.mDatabase = FirebaseDatabase.getInstance().getReference();

        setUpButtons();

        setUpListeners();

    }

    @Override
    public void setUpButtons() {
        this.addCardInActivityButton = findViewById(R.id.addCardInActivityButton);
        this.addCardReturnButton = findViewById(R.id.addCardReturnButton);
    }

    @Override
    public void setUpListeners() {
        this.addCardReturnButton.setOnClickListener( e -> {
            finish();
        });

        this.addCardInActivityButton.setOnClickListener( e -> {
            this.writeNewUser("testUser", "HelloWorld", "helloworld@mailhello.com", "abc123", false);
        });
    }

    private void addNewCard(String userId, String cardId, String cardNFC){
        //Query query = this.mDatabase.child("userData").equalTo()
        // Test user for test :)
        User user = new User("HelloWorld", "helloworld@mailhello.com", "abc123", false);

        //TODO

    }

    //Det här var bara för test. Det funkar :)
    private void writeNewUser(String userId, String userName, String email, String password, boolean admin){
        User user = new User(userName, email, password, admin);

        this.mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener( l -> {
            Log.d("Firebase", "Added user successfully");
        })
                .addOnFailureListener( l -> {
            Log.d("Firebase", "Failed to add user");
        });

    }
}