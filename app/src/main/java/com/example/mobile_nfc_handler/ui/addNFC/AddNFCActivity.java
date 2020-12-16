package com.example.mobile_nfc_handler.ui.addNFC;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import com.example.mobile_nfc_handler.BaseActivity;
import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.UISetup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *  Activity Class where Users can add a new NFC card.
 */
public class AddNFCActivity extends BaseActivity implements UISetup,NFCListener {

    private Button addCardReturnButton;
    private Button addCardInActivityButton;

    private DatabaseReference mDatabase;

    public static final String TAG = AddNFCActivity.class.getSimpleName();
    private EditText mEtMessage;
    private Button mBtWrite;
    private Button mBtRead;

    private NFCWrite mNfcWriteFragment;
    private NFCRead mNfcReadFragment;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        setUpComponents();
        setUpListeners();

        initViews();
        initNFC();
    }

    @Override
    public void setUpComponents() {
        this.addCardInActivityButton = findViewById(R.id.addCardInActivityButton);
        this.addCardReturnButton = findViewById(R.id.addCardReturnButton);
    }

    @Override
    public void setUpListeners() {
        this.addCardReturnButton.setOnClickListener( e -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.addCardInActivityButton.setOnClickListener( e -> {
            //TODOt
        });
    }

    private void addNewCard(String userId, String cardId, String cardNFC){
        //Query query = this.mDatabase.child("userData").equalTo()
        // Test user for test :)

        //TODO

    }

    //Det här var bara för test. Det funkar :)
    private void writeNewUser(String uid){
        //todo
    }


    @Override
    public void onDialogDisplayed() {
        isDialogDisplayed = true;
    }

    @Override
    public void onDialogDismissed() {
        isDialogDisplayed = false;
        isWrite = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if(mNfcAdapter!= null)
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mNfcAdapter!= null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.d(TAG, "onNewIntent: " + intent.getAction());

        if (tag != null) {
            Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
            Ndef ndef = Ndef.get(tag);
            if (isDialogDisplayed) {
                if (isWrite) {
                    String messageToWrite = mEtMessage.getText().toString();
                    mNfcWriteFragment = (NFCWrite) getFragmentManager().findFragmentByTag(NFCWrite.TAG);
                    mNfcWriteFragment.onNfcDetected(ndef, messageToWrite);
                } else {
                    mNfcReadFragment = (NFCRead) getFragmentManager().findFragmentByTag(NFCRead.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);
                }
            }
        }
    }

    private void initViews() {
        mEtMessage = (EditText) findViewById(R.id.messageToWrite);
        mBtWrite = (Button) findViewById(R.id.writeToTag);
        mBtRead = (Button) findViewById(R.id.readTag);

        mBtWrite.setOnClickListener(view -> showWrite());
        mBtRead.setOnClickListener(view -> showRead());
    }

    private void initNFC(){
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }
    private void showWrite() {
        isWrite = true;
        mNfcWriteFragment = (NFCWrite) getFragmentManager().findFragmentByTag(NFCWrite.TAG);
        if (mNfcWriteFragment == null) {
            mNfcWriteFragment = NFCWrite.newInstance();
        }
        mNfcWriteFragment.show(getFragmentManager(),NFCWrite.TAG);
    }

    private void showRead() {
        mNfcReadFragment = (NFCRead) getFragmentManager().findFragmentByTag(NFCRead.TAG);
        if (mNfcReadFragment == null) {
            mNfcReadFragment = NFCRead.newInstance();
        }
        mNfcReadFragment.show(getFragmentManager(),NFCRead.TAG);
    }
}
