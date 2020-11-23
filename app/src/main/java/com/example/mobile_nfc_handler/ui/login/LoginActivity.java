package com.example.mobile_nfc_handler.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.LoginAuthentication;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.example.mobile_nfc_handler.ui.main_menu.MainMenuActivity;
import com.example.mobile_nfc_handler.ui.register.RegisterActivity;
import com.example.mobile_nfc_handler.ui.retrivePassword.ForgotPasswordActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements UISetup {

    private TextView username;
    private TextView password;

    private Button loginButton;
    private Button registerButton;
    private Button forgotPasswordButton;

    private boolean loginResult = false;

    public static User loggedInUser;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        FirebaseUser testUser = mAuth.getCurrentUser();

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);
        this.setUpComponents();
        this.setUpListeners();
    }


    @Override
    public void setUpComponents() {
        // Id's for Buttons
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        forgotPasswordButton = findViewById(R.id.ForgotPasswordButton);

        //id's for TextFields
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

    }

    @Override
    public void setUpListeners() {
        //Login
        //test2
        loginButton.setOnClickListener(v -> {
            LoginAuthentication loginAuth = new LoginAuthentication();
            try {
                loginResult = loginAuth.login(this.username.getText().toString(), this.password.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            loginResult = true;
            if ( loginResult){
                startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                loggedInUser = new User("Anton", "Anton@swagmail.com", "abc123", false);
            }



        });

        //Register
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        //Forgot password
        forgotPasswordButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}