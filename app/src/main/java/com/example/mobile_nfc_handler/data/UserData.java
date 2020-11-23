package com.example.mobile_nfc_handler.data;

import com.example.mobile_nfc_handler.data.User;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Users data that each User has.
 */
@IgnoreExtraProperties
public class UserData {

    // Det är public för att Firebase vill ha det så
    public String userId;
    public Map<String,String> cards;

    public UserData (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserData(User user, HashMap cards ){
        this.userId = user.userName;
        this.cards = cards;
    }

    public void addCardToUserData(String cardName, String cardData){
        if ( cards.containsKey(cardName)){
            return;
        }
        else {
            cards.put(cardName, cardData);
        }
    }

    public void removeCardFromUserData(String cardName){
        if ( cards.containsKey(cardName)){
            cards.remove(cardName);
        }
    }

    public Map<String, String> getCards(){
        return cards;
    }
}
