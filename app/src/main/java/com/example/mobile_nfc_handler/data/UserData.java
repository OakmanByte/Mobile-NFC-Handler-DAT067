package com.example.mobile_nfc_handler.data;

import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.ui.showNFC.ShowNFCActivity;
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
    public User owner;
    //Ska vara en lista med NFCdata senare just nu Strings som placeholder:)
    public List<NFCData> cards;

    public UserData (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserData(User user, ArrayList cards ){
        this.owner = user;
        this.cards = cards;
    }

    public void addCardToUserData(NFCData nfcTag){
        if (this.cards.contains(nfcTag)){
            return;
        }
        else{
            this.cards.add(nfcTag);
        }
    }

    public void removeCardFromUserData(String nfcTag){
        this.cards.remove(nfcTag);
    }

    public List<NFCData> getCards(){
        return cards;
    }

    public User getOwner(){
        return owner;
    }
}
