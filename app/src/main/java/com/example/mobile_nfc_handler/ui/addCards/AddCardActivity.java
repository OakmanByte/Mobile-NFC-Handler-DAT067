package com.example.mobile_nfc_handler.ui.addCards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.UISetup;

public class AddCardActivity extends AppCompatActivity implements UISetup {

    private Button addCardReturnButton;
    private Button addCardInActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        setUpButtons();

        setUpListeners();

    }

    @Override
    public void setUpButtons() {
        this.addCardInActivityButton = findViewById(R.id.addCardInActivityButton);
        this.addCardReturnButton = findViewById(R.id.addCardReturnButton);
    }

    @Override
    public void setUpListeners() {
        this.addCardReturnButton.setOnClickListener( e -> {
            finish();
        });

        this.addCardInActivityButton.setOnClickListener( e -> {
            //todo
        });
    }
}