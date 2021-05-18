package com.hcmus.android.weshare.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.retrofit.ApiInterface;
import com.hcmus.android.weshare.retrofit.RetrofitUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    private final MutableLiveData<User> user = new MutableLiveData<>();

    public void loadUser(String uid) {
        RetrofitUtility.getInstance().getRetrofitClient().create(ApiInterface.class)
                .getUser(uid).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void updateUser(String uid, User updateUser) {
        RetrofitUtility.getInstance().getRetrofitClient().create(ApiInterface.class)
                .updateUser(uid, updateUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public LiveData<User> getUser() {
        return user;
    }
}
