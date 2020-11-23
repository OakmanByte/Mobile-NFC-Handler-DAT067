package com.example.mobile_nfc_handler.ui.retrivePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_nfc_handler.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 *  Activity Class for retriveing an Users password
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    //ActivitySetup setup = new ActivitySetup( this);
    private Button RetrivePassword;
    private Button ReturnButton;
    private TextView editTextTextEmailAddress;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();

        this.setUpComponents();
        this.setUpListeners();
    }

    public void setUpComponents() {

        this.RetrivePassword = findViewById(R.id.RetrivePassword);
        this.ReturnButton = findViewById(R.id.returnButtonForgotPassword);
        this.editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
    }



    public void setUpListeners() {
        // Button listener
        this.RetrivePassword.setOnClickListener(e -> {
            mAuth.sendPasswordResetEmail(this.editTextTextEmailAddress.getText().toString()).addOnCompleteListener(task -> {
               if(task.isSuccessful()){
                   Toast.makeText(ForgotPasswordActivity.this, "Instructions on how to reset your password has been sent to your email", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(ForgotPasswordActivity.this, "Failed to send instructions how to reset your password", Toast.LENGTH_SHORT).show();
               }
            });
        });

        this.ReturnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}
