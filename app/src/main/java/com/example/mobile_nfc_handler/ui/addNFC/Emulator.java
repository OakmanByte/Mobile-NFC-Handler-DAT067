package com.example.mobile_nfc_handler.ui.addNFC;

import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Emulator extends HostApduService {

    String TAG = "Host Card Emulator";
    String STATUS_SUCCESS = "9000";
    String STATUS_FAILED = "6F00";
    String CLA_NOT_SUPPORTED = "6E00";
    String INS_NOT_SUPPORTED = "6D00";
    String AID = "A0000002471001";
    String SELECT_INS = "A4";
    String DEFAULT_CLA = "00";
    int MIN_APDU_LENGTH = 12;

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        if (commandApdu == null) {
            return Utils.hexStringToByteArray(STATUS_FAILED);
        }
        String hexCommandApdu = Utils.toHex(commandApdu);

        if(hexCommandApdu.length() < MIN_APDU_LENGTH) {
            return Utils.hexStringToByteArray(STATUS_FAILED);
        } else if(!hexCommandApdu.substring(0, 2).equals(DEFAULT_CLA)) {
            return Utils.hexStringToByteArray(CLA_NOT_SUPPORTED);
        } else if(!hexCommandApdu.substring(2, 4).equals(SELECT_INS)) {
            return Utils.hexStringToByteArray(INS_NOT_SUPPORTED);
        } else if(hexCommandApdu.substring(10, 24).equals(AID))  {
            return Utils.hexStringToByteArray(STATUS_SUCCESS);
        } else {
            return Utils.hexStringToByteArray(STATUS_FAILED);
        }
    }

    @Override
    public void onDeactivated(int reason) {
        System.out.println(reason);
    }
}