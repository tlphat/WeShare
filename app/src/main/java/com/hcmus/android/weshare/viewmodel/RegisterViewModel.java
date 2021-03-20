package com.hcmus.android.weshare.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.model.RegisterModel;

public class RegisterViewModel extends AndroidViewModel {

    private final RegisterModel registerModel;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        registerModel = new RegisterModel(application);
        userMutableLiveData = registerModel.getUserMutableLiveData();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void register(String email, String password) {
        registerModel.register(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
