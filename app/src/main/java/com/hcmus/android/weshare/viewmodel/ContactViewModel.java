package com.hcmus.android.weshare.viewmodel;

import com.google.gson.JsonObject;

public class ContactViewModel {

    private String email;

    public ContactViewModel (String email) {
        this.email = email;
    }

    public ContactViewModel (JsonObject contactPackage) {
        this.email = contactPackage.get("email").getAsString();
    }

    public String getEmail() {
        return email;
    }
}
