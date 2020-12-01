package com.example.mobile_nfc_handler.data;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NFCData {

    //Placeholder
    private String nfcName;
    private String nfcID;

    public NFCData(){

    }

    public NFCData(String name, String nfcID){
        this.nfcName = name;
        this.nfcID = nfcID;
    }

    public String getName(){
        return this.nfcName;
    }

    public String getNfcID(){
        return this.nfcID;
    }

}
