package com.example.mobile_nfc_handler.ui.showNFC;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.NFCData;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.data.UserData;
import com.example.mobile_nfc_handler.tools.DatabaseHandling;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  Activity Class for displaying an Users saved NFC cards.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class ShowNFCActivity extends AppCompatActivity implements UISetup {

    private Button returnButtonShowCards;
    private Button testButton;
    private ListView cardList;
    private List<String>cards;
    private UserData cardData;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    private ArrayAdapter<String> adapter;

    private User userInfo;

    private FirebaseAuth mAuth;
    private FirebaseUser loggedInUser;
    private String uid;
    private FirebaseDatabase db;

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
        loggedInUser = mAuth.getCurrentUser();
        System.out.println("Logged in user: " + loggedInUser);
        uid = loggedInUser.getUid();
        // Get our instance of the database
        this.db = FirebaseDatabase.getInstance();
    }

    /*
    private void dbListeners(){
        this.db.getReference().child("userData").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ShowNFCActivity.this.cardData = snapshot.getValue(UserData.class);
                System.out.println("Tried to update card data in view");
                System.out.println("the snapshot: " + snapshot.toString());

                // Loops through the users cards and prints them out in the UI
                for( NFCData data : ShowNFCActivity.this.cardData.getCards()) {
                    ShowNFCActivity.this.cards.add(" Card name:\t" + data.getName() + "\n Card ID:\t" + data.getNfcID());
                    ShowNFCActivity.this.adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Ignore
            }
        });
    }
     */

    @Override
    public void setUpComponents() {
        this.returnButtonShowCards = findViewById(R.id.returnButtonShowCards);
        this.testButton = findViewById(R.id.Test);
        this.cardList = findViewById(R.id.CardList);
    }

    @Override
    public void setUpListeners() {
        this.returnButtonShowCards.setOnClickListener(e ->{
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.testButton.setOnClickListener(e ->{
            DatabaseHandling.readNFCDatabase(this.db, this.cards, this.adapter,this.uid);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });


    }
}