package com.hcmus.android.weshare.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.RegisterActivity;
import com.hcmus.android.weshare.repository.AuthenticationRepository;

public class AuthenticationViewModel extends AndroidViewModel {

    private final AuthenticationRepository authenticationRepository;
    private final LiveData<FirebaseUser> userMutableLiveData;

    /** The shared preferences are used to store latest login information */
    private SharedPreferences latestLoginPreferences;
    private SharedPreferences.Editor latestLoginEditor;

    public AuthenticationViewModel(@NonNull Application application) {
        super(application);
        authenticationRepository = new AuthenticationRepository(application);
        userMutableLiveData = authenticationRepository.getUserMutableLiveData();
        initSharePreferences(application);
    }

    @SuppressLint("CommitPrefEdits")
    private void initSharePreferences(Application context) {
        latestLoginPreferences = context.getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        latestLoginEditor = latestLoginPreferences.edit();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void register(String email, String password) {
        if (email.length() > 0 && password.length() > 0) {
            authenticationRepository.register(email, password);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void login(String email, String password) {
        if (email.length() > 0 && password.length() > 0) {
            authenticationRepository.login(email, password);
        }
    }

    public void storeLoginInfo(String email, String password) {
        latestLoginEditor.putString("email", email);
        latestLoginEditor.putString("password", password);
        latestLoginEditor.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void loginWithLatestInfo() {
        String latestEmail = latestLoginPreferences.getString("email", "");
        String latestPassword = latestLoginPreferences.getString("password", "");
        login(latestEmail, latestPassword);
    }

    public LiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public LiveData<String> getUserEmail() {
        return authenticationRepository.getUserEmail();
    }

    public LiveData<String> getUserPassword() {
        return authenticationRepository.getUserPassword();
    }
}
