package com.hcmus.android.weshare.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.retrofit.ApiInterface;
import com.hcmus.android.weshare.retrofit.RetrofitUtility;
import com.hcmus.android.weshare.viewmodel.SearchItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultRepository {

    private final Context context;

    private final MutableLiveData<List<SearchItemViewModel>> items = new MutableLiveData<>();

    public SearchResultRepository(Context context) {
        this.context = context;
        items.setValue(new ArrayList<>());
    }

    public void retrieveUsers(String name) {
        RetrofitUtility.getInstance().getRetrofitClient().create(ApiInterface.class)
                .getUsersByName(name).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Retrieve successful", response.body().toString());
                    List<SearchItemViewModel> searchItemList = new ArrayList<>();
                    List<User> responseList = response.body();
                    for (User item: responseList) {
                        searchItemList.add(new SearchItemViewModel(item));
                    }
                    items.postValue(searchItemList);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<SearchItemViewModel>> getSearchItemViewModels() {
        return items;
    }
}
