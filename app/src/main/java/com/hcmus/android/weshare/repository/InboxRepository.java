package com.hcmus.android.weshare.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.R;
import com.hcmus.android.weshare.viewmodel.MessageViewModel;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InboxRepository {

    private final String uuid;
    private final Context context;

    private PubNub pubnub;
    private final MutableLiveData<List<MessageViewModel>> messageViewModels = new MutableLiveData<>();

    public InboxRepository(Context context, String uuid) {
        this.context = context;
        this.uuid = uuid;
        PNConfiguration config = initPubNubConfig();
        initPubNub(config);

        messageViewModels.setValue(new ArrayList<>());
    }

    private void initPubNub(PNConfiguration config) {
        pubnub = new PubNub(config);
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                JsonObject messagePackage = message.getMessage().getAsJsonObject();
                List<MessageViewModel> messageViewModels = InboxRepository.this.messageViewModels.getValue();
                messageViewModels.add(new MessageViewModel(messagePackage));
                InboxRepository.this.messageViewModels.postValue(messageViewModels);
            }
            @Override
            public void status(PubNub pubnub, PNStatus pnStatus) {}
            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult pnPresenceEventResult) {}
            @Override
            public void signal(PubNub pubnub, PNSignalResult pnSignalResult) {}
            @Override
            public void uuid(PubNub pubnub, PNUUIDMetadataResult pnUUIDMetadataResult) {}
            @Override
            public void channel(PubNub pubnub, PNChannelMetadataResult pnChannelMetadataResult) {}
            @Override
            public void membership(PubNub pubnub, PNMembershipResult pnMembershipResult) {}
            @Override
            public void messageAction(PubNub pubnub, PNMessageActionResult pnMessageActionResult) {}
            @Override
            public void file(PubNub pubnub, PNFileEventResult pnFileEventResult) {}
        });
    }

    private PNConfiguration initPubNubConfig() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(context.getString(R.string.pubnub_subscribe_key));
        pnConfiguration.setPublishKey(context.getString(R.string.pubnub_publish_key));
        pnConfiguration.setUuid(uuid);
        return pnConfiguration;
    }

    public void sendMessage(JsonObject msg) {
        pubnub.publish()
                .message(msg)
                .channel("test_channel")
                .async((result, status) -> {});
    }

    public void registerChannel(String channelName) {
        pubnub.subscribe().channels(Collections.singletonList(channelName)).execute();
    }

    public LiveData<List<MessageViewModel>> getMessageViewModels() {
        return messageViewModels;
    }

}
