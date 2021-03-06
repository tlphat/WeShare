package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.repository.InboxRepository;

import java.util.List;

public class InboxViewModel extends AndroidViewModel {

    private InboxRepository inboxRepository;
    private LiveData<List<MessageViewModel>> messageViewModels;

    public InboxViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRepository(String uuid) {
        inboxRepository = new InboxRepository(getApplication(), uuid);
        messageViewModels = inboxRepository.getMessageViewModels();
    }

    public void registerChannel(String channelName) {
        inboxRepository.registerChannel(channelName);
    }

    public void sendMessage(User sender, String message, String channelName) {
        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("identity", sender.getDisplayName());
        messageJsonObject.addProperty("content", message);
        messageJsonObject.addProperty("sender_id", sender.getId());
        inboxRepository.sendMessage(messageJsonObject, channelName);
    }

    public LiveData<List<MessageViewModel>> getMessageViewModels() {
        return messageViewModels;
    }

    public void fetchMessages(String channelName) {
        inboxRepository.fetchMessage(channelName);
    }

    public void clearChatHistory(String channelName) {
        inboxRepository.clearChatHistory(channelName);
    }
}
