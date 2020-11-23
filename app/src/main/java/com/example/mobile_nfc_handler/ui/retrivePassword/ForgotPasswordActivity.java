package com.example.mobile_nfc_handler.ui.retrivePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import com.example.mobile_nfc_handler.R;

/**
 *  Activity Class for retriveing an Users password
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    //ActivitySetup setup = new ActivitySetup( this);
    private Button RetrivePassword;
    private Button ReturnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        this.setUpComponents();
        this.setUpListeners();
    }

    public void setUpComponents() {

        this.RetrivePassword = findViewById(R.id.RetrivePassword);
        this.ReturnButton = findViewById(R.id.returnButtonForgotPassword);
    }



    public void setUpListeners() {
        // Button listener
        this.RetrivePassword.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.ReturnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}
