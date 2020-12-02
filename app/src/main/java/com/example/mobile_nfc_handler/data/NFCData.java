package com.example.mobile_nfc_handler.data;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NFCData {

    //Placeholder
    public String nfcName;
    public String nfcID;

    public NFCData(){

    }

    public NFCData(String name, String nfcID){
        this.nfcName = name;
        this.nfcID = nfcID;
    }

    public String getNfcID(){
        return this.nfcID;
    }

    public String getName(){
        return this.nfcName;
    }


}
