package com.example.mobile_nfc_handler;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_nfc_handler.database.DatabaseInformation;
import com.example.mobile_nfc_handler.ui.login.LoginActivity;
import com.example.mobile_nfc_handler.ui.main_menu.MainMenuActivity;
import com.example.mobile_nfc_handler.ui.settings.SettingsActivity;
import com.example.mobile_nfc_handler.ui.showNFC.ShowNFCActivity;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        //toolbar backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //Handle login for all cases except if user is at the login screen
        if (id == R.id.logoutMenuItem && !(this instanceof LoginActivity)) {

                DatabaseInformation.getmAuth().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        }
        //Go to settings screen
        if (id == R.id.SettingsMenuItem) {

            startActivity(new Intent(this, SettingsActivity.class));
        }

        if (id == android.R.id.home && !(this instanceof LoginActivity)) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
            return super.onOptionsItemSelected(item);
    }


}
