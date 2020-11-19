package com.example.mobile_nfc_handler.ui.showCards;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.UISetup;


public class ShowCardsActivity extends AppCompatActivity implements UISetup {

    private Button returnButtonShowCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);

        setUpButtons();
        setUpListeners();
    }


    @Override
    public void setUpButtons() {
        this.returnButtonShowCards = findViewById(R.id.returnButtonShowCards);
    }

    @Override
    public void setUpListeners() {
        this.returnButtonShowCards.setOnClickListener(e ->{
            finish();
        });
    }
}