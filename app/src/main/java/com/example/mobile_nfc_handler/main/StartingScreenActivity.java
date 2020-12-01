package com.example.mobile_nfc_handler.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.data.UserData;
import com.example.mobile_nfc_handler.database.DatabaseInformation;
import com.example.mobile_nfc_handler.ui.login.LoginActivity;

public class StartingScreenActivity extends AppCompatActivity {

    public static User theUser;
    public static UserData theUsersData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen2);

            //Initialize the database information
            new DatabaseInformation();

            startActivity(new Intent(StartingScreenActivity.this, LoginActivity.class));

        }
}
