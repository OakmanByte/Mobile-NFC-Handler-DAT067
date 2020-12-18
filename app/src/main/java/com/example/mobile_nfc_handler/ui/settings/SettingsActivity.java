package com.example.mobile_nfc_handler.ui.settings;
// https://www.geeksforgeeks.org/how-to-implement-dark-night-mode-in-android-app/
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;

import com.example.mobile_nfc_handler.BaseActivity;
import com.example.mobile_nfc_handler.R;

public class SettingsActivity extends BaseActivity {

    private Switch darkModeSwitch;
    private boolean isDarkModeOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        darkModeSwitch = findViewById(R.id.darkModeSwitch);

       // SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        //final SharedPreferences.Editor editor;
        //editor = sharedPreferences.edit();
       // isDarkModeOn  = sharedPreferences.getBoolean("isDarkModeOn", false);
        isDarkModeOn = false;

/*
        if (isDarkModeOn) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            darkModeSwitch.setText("Disable Dark Mode");
        }

        else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            darkModeSwitch.setText("Enable Dark Mode");
        }
        */

        darkModeSwitch.setOnCheckedChangeListener((view,b) -> {

            System.out.println("HEJ TOR HUR MÅR DU?");
            System.out.println(b);

            if (b) {

              //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                setTheme(R.style.Theme_AppCompat_DayNight);

                        darkModeSwitch.setText("Disable Dark Mode");
                    }

            else {

                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                setTheme(R.style.Theme_AppCompat_Light);

                        darkModeSwitch.setText("Enable Dark Mode");
                    }

            isDarkModeOn = b;
        });
    }
}