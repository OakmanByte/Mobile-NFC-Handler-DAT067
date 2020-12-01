package com.example.mobile_nfc_handler.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.example.mobile_nfc_handler.ui.main_menu.MainMenuActivity;
import com.example.mobile_nfc_handler.ui.register.RegisterActivity;
import com.example.mobile_nfc_handler.ui.retrivePassword.ForgotPasswordActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 *  Activity Class for logging in as an User.
 */
public class LoginActivity extends AppCompatActivity implements UISetup {

    private TextView username;
    private TextView password;

    private Button loginButton;
    private Button registerButton;
    private Button forgotPasswordButton;

    private boolean loginResult = false;

    private FirebaseUser loggedInUser;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
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
        loginButton.setOnClickListener(v -> {
            this.mAuth.signInWithEmailAndPassword(this.username.getText().toString(), this.password.getText().toString()).addOnCompleteListener( task -> {
                // If credentials are correct
               if(task.isSuccessful()){
                   FirebaseUser user = mAuth.getCurrentUser();

                   if(user.isEmailVerified()){
                       // Go to the next view :D
                       System.out.println("Successful login");
                       startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                       overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                   }
                   else {
                       this.mAuth.signOut();
                       Toast.makeText(LoginActivity.this, "Email is not verified", Toast.LENGTH_SHORT).show();
                   }
               }
               else{
                   Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
               }
            });
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