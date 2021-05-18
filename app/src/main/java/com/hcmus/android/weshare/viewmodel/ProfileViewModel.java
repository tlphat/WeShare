package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.repository.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final ProfileRepository repository = new ProfileRepository();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<User> getUser() {
        return repository.getUser();
    }

    public void loadUser(String uid) {
        repository.loadUser(uid);
    }

    public void updateUser(String uid, User user) {
        repository.updateUser(uid, user);
    }
}
