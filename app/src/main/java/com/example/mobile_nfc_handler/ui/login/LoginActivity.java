package com.example.mobile_nfc_handler.ui.login;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.Utility;
import com.example.mobile_nfc_handler.database.DatabaseHandling;
import com.example.mobile_nfc_handler.database.DatabaseInformation;
import com.example.mobile_nfc_handler.BaseActivity;
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
public class LoginActivity extends BaseActivity implements UISetup {

    private TextView username;
    private TextView password;

    private Button loginButton;
    private Button registerButton;
    private Button forgotPasswordButton;

    private boolean loginResult = false;

    String usernameText;
    String passwordText;

    private FirebaseUser loggedInUser;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        mAuth = DatabaseInformation.getmAuth();
        setContentView(R.layout.activity_login);
        this.setUpComponents();
        this.setUpListeners();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }
*/
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.logoutMenuItem){

           //
        }

        return super.onOptionsItemSelected(item);
    }
*/
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
            this.usernameText = this.username.getText().toString();
            this.passwordText = this.password.getText().toString();

            System.out.println(this.usernameText + this.passwordText  );

            if(!(Utility.isStringNullorEmpty(this.usernameText)) && !(Utility.isStringNullorEmpty(this.passwordText))) {

                this.mAuth.signInWithEmailAndPassword(this.usernameText, this.passwordText).addOnCompleteListener(task -> {
                    // If credentials are correct
                    if (task.isSuccessful()) {

                        if (mAuth.getCurrentUser().isEmailVerified()) {
                            System.out.println("AM HERE");
                            // Go to the next view :D
                            // Fetch the logged in users data from the database
                            DatabaseHandling.getUserFromDatabase(mAuth.getUid());
                            // Fetch the userData from the database
                            DatabaseHandling.getUserDataFromDatabase(mAuth.getUid());
                            // Go to the main menu
                            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class).putExtra("from", this.getClass().getSimpleName()));;
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else {
                            this.mAuth.signOut();
                            Toast.makeText(LoginActivity.this, "Email is not verified", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                    }
                });
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