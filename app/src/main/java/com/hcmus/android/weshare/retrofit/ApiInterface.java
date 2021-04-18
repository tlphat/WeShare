package com.hcmus.android.weshare.retrofit;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("channels/{uid}")
    Call<List<ContactViewModel>> getContacts(@Path("uid") String uid);

    @GET("users/{uid}")
    Call<User> getUser(@Path("uid") String uid);

    @POST("users")
    Call<User> saveUser(@Body User user);
}
