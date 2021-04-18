package com.hcmus.android.weshare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcmus.android.weshare.retrofit.ApiInterface;
import com.hcmus.android.weshare.retrofit.RetrofitUtility;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListRepository {

    private final Context context;

    private final MutableLiveData<List<ContactViewModel>> friendList = new MutableLiveData<>();

    public ContactListRepository(Context context) {
        this.context = context;
        friendList.setValue(new ArrayList<>());
    }

    public void retrieveFriends(String userID) {
        RetrofitUtility.getInstance().getRetrofitClient().create(ApiInterface.class)
                .getContacts(userID).enqueue(new Callback<List<ContactViewModel>>() {
            @Override
            public void onResponse(Call<List<ContactViewModel>> call,
                                   Response<List<ContactViewModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Retrieve successful", response.body().toString());
                    friendList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ContactViewModel>> call, Throwable t) {
                Log.e("Retrieve failed", t.toString());
            }
        });
    }

    public LiveData<List<ContactViewModel>> getContactViewModels() {
        return friendList;
    }
}
