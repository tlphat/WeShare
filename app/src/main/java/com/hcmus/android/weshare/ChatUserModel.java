package com.hcmus.android.weshare;

public class ChatUserModel {
    private String email, message;

    public ChatUserModel(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public ChatUserModel() {
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
