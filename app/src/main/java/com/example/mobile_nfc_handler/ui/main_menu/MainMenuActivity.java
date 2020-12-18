package com.example.mobile_nfc_handler.ui.main_menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.BaseActivity;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.example.mobile_nfc_handler.ui.nfcUNUSED.AddNFCActivity;
import com.example.mobile_nfc_handler.ui.showNFC.ShowNFCActivity;

/**
 *  Activity Class that is used as a main menu for users to easier navigate though the application
 */
public class MainMenuActivity extends BaseActivity implements UISetup {

    private Button addCardButton;
    private Button showCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.setUpComponents();
        this.setUpListeners();
        System.out.println("I AM HERE IN MENU");
    }


    @Override
    public void setUpComponents(){
        this.addCardButton = findViewById(R.id.addCardButton);
        this.showCardButton = findViewById(R.id.showCardButton);
    }

    @Override
    public void setUpListeners(){
        // Button listener
        showCardButton.setOnClickListener( e -> {
            //Go to the show cards activity
            startActivity(new Intent(MainMenuActivity.this, ShowNFCActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        addCardButton.setOnClickListener( e -> {
            //Go to the add cards activity
            startActivity(new Intent(MainMenuActivity.this, AddNFCActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}