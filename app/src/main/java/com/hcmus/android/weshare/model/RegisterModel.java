package com.hcmus.android.weshare.model;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterModel {

    private final Application application;
    private final FirebaseAuth firebaseAuth;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;

    public RegisterModel(Application application) {
        this.application = application;
        firebaseAuth = FirebaseAuth.getInstance();
        userMutableLiveData = new MutableLiveData<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(),
                task -> {
                    if (task.isSuccessful()) {
                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                    } else {
                        Log.e("RegisterModel", "Error creating user!");
                    }
                });
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
