package com.example.mobile_nfc_handler.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.mobile_nfc_handler.data.LocalUserData;
import com.example.mobile_nfc_handler.generic.Result;
import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.User;

public class LoginViewModel extends ViewModel {


    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LocalUserData localUserData;

    LoginViewModel(LocalUserData localUserData) {
        this.localUserData = localUserData;
    }


    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

}