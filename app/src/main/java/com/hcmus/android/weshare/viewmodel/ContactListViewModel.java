package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.repository.ContactListRepository;
import com.hcmus.android.weshare.repository.InboxRepository;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {

    private ContactListRepository contactListRepository;
    private LiveData<List<ContactViewModel>> contactViewModels;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRepository(String uuid) {

    }

    public void registerChannel(String channelName) {
    }


    public LiveData<List<ContactViewModel>> getContactViewModels() {
        return null;
    }
}
