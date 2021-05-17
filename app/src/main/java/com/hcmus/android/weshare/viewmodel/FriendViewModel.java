package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.repository.FriendRepository;

public class FriendViewModel extends AndroidViewModel {

    private FriendRepository friendRepository;

    public FriendViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRepository() {
        friendRepository = new FriendRepository();
    }

    public void addContact(User fromUser, User toUser) {
        friendRepository.addContact(fromUser, toUser);
    }
}
