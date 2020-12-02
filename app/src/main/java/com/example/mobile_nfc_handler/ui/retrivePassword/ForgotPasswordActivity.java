package com.example.mobile_nfc_handler.ui.retrivePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.Utility;
import com.example.mobile_nfc_handler.database.DatabaseInformation;
import com.google.firebase.auth.FirebaseAuth;

/**
 *  Activity Class for retriveing an Users password
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    private Button retrivePassword;
    private Button returnButton;
    private TextView editTextTextEmailAddress;
    private String emailInput;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = DatabaseInformation.getmAuth();

        this.setUpComponents();
        this.setUpListeners();
    }

    public void setUpComponents() {

        this.retrivePassword = findViewById(R.id.RetrivePassword);
        this.returnButton = findViewById(R.id.returnButtonForgotPassword);
        this.editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        this.emailInput = this.editTextTextEmailAddress.getText().toString();
    }



    public void setUpListeners() {
        // Button listener
        this.retrivePassword.setOnClickListener(e -> {

            if(!(Utility.isStringNullorEmpty(this.emailInput))) {

                mAuth.sendPasswordResetEmail(this.emailInput).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this, "Instructions on how to reset your password has been sent to your email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Failed to send instructions how to reset your password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        this.returnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}
