package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcmus.android.weshare.repository.ContactListRepository;

import java.util.List;

// TODO implement this view model and include it into ContactActivity
public class ContactListViewModel extends AndroidViewModel {

    private ContactListRepository contactListRepository;
    private MutableLiveData<List<ContactViewModel>> contactViewModels;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initContactListRepository(String uid) {
        contactListRepository = new ContactListRepository(getApplication(), uid);
        contactListRepository.retrieveFriends();
        contactViewModels = contactListRepository.getContactViewModels();
    }

    public LiveData<List<ContactViewModel>> getContactViewModels() {
        return contactViewModels;
    }

}
