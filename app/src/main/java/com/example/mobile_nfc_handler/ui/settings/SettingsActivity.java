package com.example.mobile_nfc_handler.ui.settings;
// https://www.geeksforgeeks.org/how-to-implement-dark-night-mode-in-android-app/
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatDelegate;

import com.example.mobile_nfc_handler.BaseActivity;
import com.example.mobile_nfc_handler.R;

public class SettingsActivity extends BaseActivity {

    private Switch darkModeSwitch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        System.out.println("is dark mode on? " + isDarkModeOn);

        darkModeSwitch.setChecked(isDarkModeOn);


        darkModeSwitch.setOnCheckedChangeListener((view,b) -> {

            System.out.println("HEJ TOR HUR MÅR DU?");
            System.out.println(b);

            if (b) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }

            else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            editor.putBoolean("isDarkModeOn", b);
            editor.apply();
            finish();
        });
    }
}