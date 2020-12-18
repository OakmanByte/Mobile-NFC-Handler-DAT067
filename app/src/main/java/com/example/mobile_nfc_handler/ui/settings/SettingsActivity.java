package com.example.mobile_nfc_handler.ui.settings;
// https://www.geeksforgeeks.org/how-to-implement-dark-night-mode-in-android-app/
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatDelegate;

import com.example.mobile_nfc_handler.BaseActivity;
import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.main_menu.MainMenuActivity;
import com.example.mobile_nfc_handler.ui.nfcUNUSED.AddNFCActivity;

public class SettingsActivity extends BaseActivity {

    private Switch myswitch;
    SharedPref sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.Theme_AppCompat_DayNight);
        }
        else  setTheme(R.style.Theme_AppCompat_Light);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        myswitch= (Switch)findViewById(R.id.darkModeSwitch);

        if (sharedpref.loadNightModeState()==true) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(i);
        finish();
    }
}