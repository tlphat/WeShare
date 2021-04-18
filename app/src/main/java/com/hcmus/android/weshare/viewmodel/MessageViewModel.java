package com.hcmus.android.weshare.viewmodel;

import com.google.gson.JsonObject;

import lombok.Getter;

@Getter
public class MessageViewModel {

    private final String content;
    private final String identity;

    public MessageViewModel(JsonObject messagePackage) {
        this.content = messagePackage.get("content").getAsString();
        this.identity = messagePackage.get("identity").getAsString();
    }

    public static boolean isMessage(JsonObject messagePackage) {
        return messagePackage.has("content")
                && messagePackage.has("identity");
    }
}
