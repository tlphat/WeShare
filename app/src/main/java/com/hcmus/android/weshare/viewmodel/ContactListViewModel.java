package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hcmus.android.weshare.repository.ContactListRepository;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {

    private ContactListRepository contactListRepository;
    private LiveData<List<ContactViewModel>> contactViewModels;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRepository() {
        contactListRepository = new ContactListRepository(getApplication());
        contactViewModels = contactListRepository.getContactViewModels();
    }

    public LiveData<List<ContactViewModel>> getContactViewModels() {
        return contactViewModels;
    }

    public void retrieveFriends(String uid) {
        contactListRepository.retrieveFriends(uid);
    }
}
