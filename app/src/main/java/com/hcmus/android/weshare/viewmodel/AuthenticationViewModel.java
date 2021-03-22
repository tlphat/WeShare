package com.hcmus.android.weshare.viewmodel;

import android.app.Application;
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

    public AuthenticationViewModel(@NonNull Application application) {
        super(application);
        authenticationRepository = new AuthenticationRepository(application);
        userMutableLiveData = authenticationRepository.getUserMutableLiveData();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void register(String email, String password) {
        authenticationRepository.register(email, password);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void login(String email, String password) {
        authenticationRepository.login(email, password);
    }

    public LiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
