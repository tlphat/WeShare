package com.hcmus.android.weshare.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hcmus.android.weshare.model.User;

import lombok.Getter;

@Getter
public class ContactViewModel {

    private String connectionID = "dummy";
    private User user;

    public ContactViewModel (String connectionID, User user) {
        this.connectionID = connectionID;
        this.user = user;
    }
}
