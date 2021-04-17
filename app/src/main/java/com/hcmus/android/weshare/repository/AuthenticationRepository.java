package com.hcmus.android.weshare.repository;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.retrofit.ApiInterface;
import com.hcmus.android.weshare.retrofit.RetrofitUtility;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationRepository {

    private final String TAG = "AppRepository";

    private final Application application;
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private final MutableLiveData<FirebaseUser> userMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> userEmail = new MutableLiveData<>();
    private final MutableLiveData<String> userPassword = new MutableLiveData<>();

    @Getter private MutableLiveData<User> user;

    public AuthenticationRepository(Application application) {
        this.application = application;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), task -> {
                    if (task.isSuccessful()) {
                        // TODO save user info to the database
                        userEmail.postValue(email);
                        userPassword.postValue(password);
                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        saveUserInfoToDB();
                    } else {
                        Log.e(TAG, "Error creating user!");
                    }
                });
    }

    private void saveUserInfoToDB() {
        ApiInterface apiService = RetrofitUtility.getRetrofitClient().create(ApiInterface.class);

        apiService.saveUser(userMutableLiveData.getValue().getUid(), userMutableLiveData.getValue().getEmail());
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), task -> {
                    if (task.isSuccessful()) {
                        // TODO load user info from the database
                        userEmail.postValue(email);
                        userPassword.postValue(password);
                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                    } else {
                        Log.e(TAG, "Error sign in!");
                    }
                });
    }

    public LiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public LiveData<String> getUserEmail() {
        return userEmail;
    }

    public LiveData<String> getUserPassword() {
        return userPassword;
    }

    public void loadUserInfoFromDB() {
        ApiInterface apiService = RetrofitUtility.getRetrofitClient().create(ApiInterface.class);

        apiService.getUser(userMutableLiveData.getValue().getUid()).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    user.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
