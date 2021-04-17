package com.hcmus.android.weshare.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.retrofit.ApiInterface;
import com.hcmus.android.weshare.retrofit.RetrofitUtility;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListRepository {

    private Context context;
    private String userID;

    private final MutableLiveData<List<ContactViewModel>> friendList = new MutableLiveData<>();

    public ContactListRepository(Context context, String userID) {
        this.context = context;
        this.userID = userID;
        friendList.setValue(new ArrayList<>());
    }

    public void retrieveFriends() {
        ApiInterface apiService = RetrofitUtility.getRetrofitClient().create(ApiInterface.class);

        apiService.getContacts(userID).enqueue(new Callback<List<ContactViewModel>>() {
            @Override
            public void onResponse(Call<List<ContactViewModel>> call, Response<List<ContactViewModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    friendList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ContactViewModel>> call, Throwable t) {

            }
        });
    }

    public void registerChannel(String channelName) {
    }

    public MutableLiveData<List<ContactViewModel>> getContactViewModels() {
        return friendList;
    }
}
