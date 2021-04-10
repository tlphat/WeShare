package com.hcmus.android.weshare.viewmodel;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.model.User;

import lombok.Getter;

@Getter
public class ContactViewModel {

    private final String connectionID = "dummy";
    private User user;
}
