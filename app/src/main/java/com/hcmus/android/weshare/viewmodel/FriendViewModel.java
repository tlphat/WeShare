package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.repository.FriendRepository;

public class FriendViewModel extends AndroidViewModel {

    private FriendRepository friendRepository;
    private LiveData<JsonObject> messageLiveData;

    public FriendViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRepository(String uuid) {
        friendRepository = new FriendRepository(getApplication(), uuid);
        messageLiveData = friendRepository.getMessageLiveData();
    }

    public void registerChannel(String channelName) {
        friendRepository.registerChannel(channelName);
    }

    public void sendMessage(User sender, boolean isInvitation, String channelName) {
        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("sender", sender.getDisplayName());
        messageJsonObject.addProperty("is_invitation", isInvitation);
        friendRepository.sendMessage(messageJsonObject, channelName);
    }

    public LiveData<JsonObject> getMessageViewModels() {
        return messageLiveData;
    }
}
