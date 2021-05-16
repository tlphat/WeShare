package com.hcmus.android.weshare.repository;

import com.hcmus.android.weshare.model.Channel;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.retrofit.ApiInterface;
import com.hcmus.android.weshare.retrofit.RetrofitUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepository {

    private final String TAG = getClass().getSimpleName();

    public void addContact(User fromUser, User toUser) {
        Channel channel = new Channel(0, fromUser.getId(), toUser.getId());
        RetrofitUtility.getInstance().getRetrofitClient().create(ApiInterface.class)
                .saveContact(channel).enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {

            }

            @Override
            public void onFailure(Call<Channel> call, Throwable t) {

            }
        });
    }
}
