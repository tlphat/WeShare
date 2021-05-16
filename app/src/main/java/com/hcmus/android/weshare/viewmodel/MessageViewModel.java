package com.hcmus.android.weshare.viewmodel;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
public class MessageViewModel {

    private final String content;
    private final String identity;
    @Accessors(fluent = true)
    private final boolean isFromCurrentUser;

    public MessageViewModel(JsonObject messagePackage, String currentUserID) {
        this.content = messagePackage.get("content").getAsString();
        this.identity = messagePackage.get("identity").getAsString();
        this.isFromCurrentUser = messagePackage.get("sender_id").getAsString().equals(currentUserID);
    }

    public static boolean isConvertible(JsonObject messagePackage) {
        return messagePackage.has("content")
                && messagePackage.has("identity")
                && messagePackage.has("sender_id");
    }
}
