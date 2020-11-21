package com.example.mobile_nfc_handler.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.example.mobile_nfc_handler.R;

public class RegisterActivity extends AppCompatActivity {

    private Button returnButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setUpComponents();
        this.setUpListeners();
    }

    public void setUpComponents() {
        this.returnButton = findViewById(R.id.returnButtonRegister);
        this.registerButton =findViewById(R.id.RegisterButton);
    }

    public void setUpListeners() {
        // Button listener
        this.returnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.registerButton.setOnClickListener(e -> {
            //TODO
        });
    }
}