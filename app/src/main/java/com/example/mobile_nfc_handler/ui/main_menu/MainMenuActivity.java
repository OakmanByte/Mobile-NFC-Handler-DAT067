package com.example.mobile_nfc_handler.ui.main_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.example.mobile_nfc_handler.ui.addCards.AddCardActivity;
import com.example.mobile_nfc_handler.ui.showCards.ShowCardsActivity;

public class MainMenuActivity extends AppCompatActivity implements UISetup {

    private Button returnButton;
    private Button addCardButton;
    private Button showCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.setUpComponents();
        this.setUpListeners();

    }

    @Override
    public void setUpComponents(){
        this.addCardButton = findViewById(R.id.addCardButton);
        this.showCardButton = findViewById(R.id.showCardButton);
        this.returnButton = findViewById(R.id.returnButtonMainMenu);
    }


    @Override
    public void setUpListeners(){
        // Button listener
        returnButton.setOnClickListener( e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
        showCardButton.setOnClickListener( e -> {
            //Go to the show cards activity
            startActivity(new Intent(MainMenuActivity.this, ShowCardsActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        addCardButton.setOnClickListener( e -> {
            //Go to the add cards activity
            startActivity(new Intent(MainMenuActivity.this, AddCardActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}