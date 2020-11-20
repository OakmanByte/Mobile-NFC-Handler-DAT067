package com.example.mobile_nfc_handler.data;

import com.example.mobile_nfc_handler.generic.Result;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LocalUserData {

    private static volatile LocalUserData instance;

    private LoginAuthentication dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private User user = null;

    // private constructor : singleton access
    private LocalUserData(LoginAuthentication dataSource) {
        this.dataSource = dataSource;
    }

    public static LocalUserData getInstance(LoginAuthentication dataSource) {
        if (instance == null) {
            instance = new LocalUserData(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(User user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<User> login(String username, String password) {
        // handle login
        Result<User> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<User>) result).getData());
        }
        return result;
    }
}