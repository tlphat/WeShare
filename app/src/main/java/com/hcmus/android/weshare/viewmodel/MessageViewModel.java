package com.hcmus.android.weshare.viewmodel;

import com.google.gson.JsonObject;

import lombok.Getter;

@Getter
public class MessageViewModel {

    private String content;
    private String email;

    public MessageViewModel(JsonObject messagePackage) {
        this.content = messagePackage.get("content").getAsString();
        this.email = messagePackage.get("email").getAsString();
    }
}
