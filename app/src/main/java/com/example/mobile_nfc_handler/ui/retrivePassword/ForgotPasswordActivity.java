package com.example.mobile_nfc_handler.ui.retrivePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.mobile_nfc_handler.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button RetrivePassword;
    private Button ReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        this.setUpButtons();
        this.setUpListeners();
    }

    public void setUpButtons() {

        this.RetrivePassword = findViewById(R.id.RetrivePassword);
        this.ReturnButton = findViewById(R.id.returnButtonForgotPassword);
    }

    public void setUpListeners() {
        // Button listener
        this.RetrivePassword.setOnClickListener(e -> {
            //Return to main activity
            finish();
        });

        this.ReturnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
        });
    }
}
