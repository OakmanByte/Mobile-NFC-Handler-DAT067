package com.example.mobile_nfc_handler.data;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NFCData {

    //Placeholder
    private String name;
    private String nfcID;

    public NFCData(){

    }

    public NFCData(String name, String nfcID){
        this.name = name;
        this.nfcID = nfcID;
    }

    public String getName(){
        return this.name;
    }

    public String getNfcID(){
        return this.nfcID;
    }

}
