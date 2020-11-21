package com.example.mobile_nfc_handler.ui.showCards;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.UISetup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ShowCardsActivity extends AppCompatActivity implements UISetup {

    private Button returnButtonShowCards;
    private Button testButton;
    private ListView cardList;
    private List<String> cards;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    private ArrayAdapter<String> adapter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);

        setUpComponents();
        setUpListeners();

        cards = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards );
        cardList.setAdapter(adapter);
    }


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
              this.cards.add("Card" + i);
              this.adapter.notifyDataSetChanged();
              i++;
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });


    }
}