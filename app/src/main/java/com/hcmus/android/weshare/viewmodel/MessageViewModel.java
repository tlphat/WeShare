package com.hcmus.android.weshare.viewmodel;

import com.hcmus.android.weshare.model.Message;

public class MessageViewModel {

    public String content;
    public String email;

    public MessageViewModel(Message message) {
        this.content = message.getContent();
        this.email = message.getEmail();
    }

}
