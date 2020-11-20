package com.example.mobile_nfc_handler.ui.addCards;

import android.app.DialogFragment;
import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mobile_nfc_handler.R;

import java.io.IOException;

public class NFCRead extends DialogFragment{
    public static final String TAG = NFCRead.class.getSimpleName();

    public static NFCRead newInstance(){
        return new NFCRead();
    }

    private TextView mTvMessage;
    private NFCListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_read,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (NFCListener) context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }
    public void onNfcDetected(Ndef ndef){
        readFromNFC(ndef);
    }

    private void readFromNFC(Ndef ndef) {
        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());
            Log.d(TAG, "readFromNFC: "+message);
            mTvMessage.setText(message);
            ndef.close();

        }catch (IOException | FormatException e) {
            e.printStackTrace();
            }
    }

}
