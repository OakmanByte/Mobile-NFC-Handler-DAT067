package com.example.mobile_nfc_handler;

import com.example.mobile_nfc_handler.data.NFCData;

import java.util.List;

public class Utility {


    public static boolean isStringNullorEmpty(String str){


        if(str== null || str.isEmpty()){
            return true;
        }
        else
            return false;
    }

    public static boolean isDataNullorEmpty(List<NFCData> data){

        if(data== null || data.isEmpty()){
            return true;
        }
        else
            return false;
    }

}
