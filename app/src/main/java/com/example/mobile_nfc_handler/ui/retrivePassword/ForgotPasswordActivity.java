package com.example.mobile_nfc_handler.ui.retrivePassword;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_nfc_handler.ui.ActivitySetup;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobile_nfc_handler.R;

import java.util.ArrayList;
import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity {

    //ActivitySetup setup = new ActivitySetup( this);
    private Button RetrivePassword;
    private Button ReturnButton;


   // List<Button> allButtons = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

       // this.getAllButtons();
       // ActivitySetup.setUpButtons(RetrivePassword,R.id.RetrivePassword );
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
