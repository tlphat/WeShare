package com.hcmus.android.weshare.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.List;

public class ContactListRepository {

    private final Application application;
    private final MutableLiveData<List<ContactViewModel>> contactViewModels = new MutableLiveData<>();
    private User user;

    public ContactListRepository(Application application) {
        this.application = application;
    }

    public LiveData<List<ContactViewModel>> getContactViewModels() {
        return contactViewModels;
    }

    public void initUser(User user) {
        this.user = user;
    }

    public void retrieveFriends() {
        // TODO get friends of current user from the database
    }
}
