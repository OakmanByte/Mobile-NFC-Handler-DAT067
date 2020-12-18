package com.example.mobile_nfc_handler.ui.settings;
// https://www.geeksforgeeks.org/how-to-implement-dark-night-mode-in-android-app/

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.example.mobile_nfc_handler.BaseActivity;
import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.Utility;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.database.DatabaseInformation;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.example.mobile_nfc_handler.ui.login.LoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;

public class SettingsActivity extends BaseActivity implements UISetup {

    private Switch darkModeSwitch;
    private Button updatePasswordButton;
    private TextView updateOldPassword;
    private TextView updateNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        darkModeSwitch.setChecked(isDarkModeOn);
        darkModeSwitch.setOnCheckedChangeListener((view, b) -> {

            if (b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            editor.putBoolean("isDarkModeOn", b);
            editor.apply();
            //Have to finish because of setDefaultNightMode bug
            finish();
        });

        setUpComponents();
        setUpListeners();
        }





    private void changePassword(String oldPassword, String newPassword) {


        FirebaseUser user = DatabaseInformation.getmAuth().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), oldPassword);

        user.reauthenticate(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                user.updatePassword(newPassword);
                Toast.makeText(this, "Password has been updated!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong old Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setUpComponents() {
        updatePasswordButton = findViewById(R.id.updatePasswordButton);
        updateOldPassword = findViewById(R.id.updateOldPassword);
        updateNewPassword = findViewById(R.id.updateNewPassword);
    }

    @Override
    public void setUpListeners() {

        this.updatePasswordButton.setOnClickListener(v -> {

            String oldPassword;
            String newPassword;

            oldPassword= this.updateOldPassword.getText().toString();
            newPassword = this.updateNewPassword.getText().toString();

            if(!(Utility.isStringNullorEmpty(oldPassword)) && !(Utility.isStringNullorEmpty(newPassword))) {

            changePassword(oldPassword, newPassword);
            }
        });
    }
}