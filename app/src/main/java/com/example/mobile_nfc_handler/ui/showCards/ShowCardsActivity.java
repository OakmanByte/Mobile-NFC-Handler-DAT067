package com.example.mobile_nfc_handler.ui.showCards;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.UserData;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.example.mobile_nfc_handler.ui.addCards.NFCService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *  Activity Class for displaying an Users saved NFC cards.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class ShowCardsActivity extends AppCompatActivity implements UISetup {

    private Button returnButtonShowCards;
    private Button testButton;
    private Button startButton;
    private Button stopButton;

    private ListView cardList;
    private List<String>cards;
    private UserData cardData;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    private ArrayAdapter<String> adapter;

    private FirebaseAuth mAuth;
    private FirebaseUser loggedInUser;
    private String uid;
    private FirebaseDatabase db;

   // ALL THIS CODE IS JUST FOR TESTING SHOULD MOST LIKELY BE REMOVED WHEN WE START WORKING ON REAL ADD CARD

    int i = 0;

    // Save current LocalDateTime into a variable
    LocalDate baseDate = LocalDate.of(2020,11,21);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);

        setUpDB();
        setUpComponents();
        setUpListeners();

        cards = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards );
        cardList.setAdapter(adapter);

    }

    private void setUpDB(){

        //Setup logged in user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser loggedInUser = mAuth.getCurrentUser();
        uid = loggedInUser.getUid();

        // Setup db data for cards of the user
        db = FirebaseDatabase.getInstance();
        db.getReference().child("userData").child(uid).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cardData = snapshot.getValue(UserData.class);
                        System.out.println("Tried to update card data in view");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //Ignore
                    }
                });
    }

    @Override
    public void setUpComponents() {
        this.returnButtonShowCards = findViewById(R.id.returnButtonShowCards);
        this.testButton = findViewById(R.id.Test);
        this.cardList = findViewById(R.id.CardList);

        this.startButton = findViewById(R.id.startButton);
        this.stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(e -> startService(new Intent(this, NFCService.class)));
        stopButton .setOnClickListener(e -> stopService(new Intent(this, NFCService.class)));

    }

    @Override
    public void setUpListeners() {
        this.returnButtonShowCards.setOnClickListener(e ->{
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.testButton.setOnClickListener(e ->{

            //Randomize a date
            /*
            int randomAmountOfDays = (int) (4000*Math.random());
            LocalDate randomdate = baseDate.plusDays(randomAmountOfDays);
            String randomDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(randomdate);
            */
            for( String s : this.cardData.cards.keySet()) {
                this.cards.add(" Card name:\t" + this.cardData.cards.get(s) + "\n Added:\t");
                this.adapter.notifyDataSetChanged();
            }
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });


    }
}