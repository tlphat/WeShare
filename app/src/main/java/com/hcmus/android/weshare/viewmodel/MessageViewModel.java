package com.hcmus.android.weshare.viewmodel;

import com.google.gson.JsonObject;

public class MessageViewModel {

    public String content;
    public String email;

    public MessageViewModel(JsonObject messagePackage) {
        this.content = messagePackage.get("content").getAsString();
        this.email = messagePackage.get("email").getAsString();
    }

}
