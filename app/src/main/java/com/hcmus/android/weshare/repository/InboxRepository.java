package com.hcmus.android.weshare.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.R;
import com.hcmus.android.weshare.viewmodel.MessageViewModel;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNHistoryItemResult;
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

    private final String TAG = getClass().getSimpleName();
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
            public void message(@Nullable PubNub pubnub, @Nullable PNMessageResult message) {
                if (message == null) {
                    Log.e(TAG, "Empty message received");
                    return;
                }
                JsonObject messagePackage = message.getMessage().getAsJsonObject();
                if (MessageViewModel.isConvertible(messagePackage)) {
                    List<MessageViewModel> messageViewModels = InboxRepository.this.messageViewModels.getValue();
                    messageViewModels.add(new MessageViewModel(messagePackage, uuid));
                    InboxRepository.this.messageViewModels.postValue(messageViewModels);
                }
            }

            @Override
            public void status(@Nullable PubNub pubnub, @Nullable PNStatus pnStatus) {
            }

            @Override
            public void presence(@Nullable PubNub pubnub, @Nullable PNPresenceEventResult pnPresenceEventResult) {
            }

            @Override
            public void signal(@Nullable PubNub pubnub, @Nullable PNSignalResult pnSignalResult) {
            }

            @Override
            public void uuid(@Nullable PubNub pubnub, @Nullable PNUUIDMetadataResult pnUUIDMetadataResult) {
            }

            @Override
            public void channel(@Nullable PubNub pubnub, @Nullable PNChannelMetadataResult pnChannelMetadataResult) {
            }

            @Override
            public void membership(@Nullable PubNub pubnub, @Nullable PNMembershipResult pnMembershipResult) {
            }

            @Override
            public void messageAction(@Nullable PubNub pubnub, @Nullable PNMessageActionResult pnMessageActionResult) {
            }

            @Override
            public void file(@Nullable PubNub pubnub, @Nullable PNFileEventResult pnFileEventResult) {
            }
        });
    }

    private PNConfiguration initPubNubConfig() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(context.getString(R.string.pubnub_subscribe_key));
        pnConfiguration.setPublishKey(context.getString(R.string.pubnub_publish_key));
        pnConfiguration.setUuid(uuid);
        return pnConfiguration;
    }

    public void sendMessage(JsonObject msg, String channelName) {
        pubnub.publish()
                .message(msg)
                .channel(channelName)
                .async((result, status) -> {
                });
    }

    public void fetchMessage(String channelName) {
        pubnub.history().channel(channelName).count(10)
                .async((result, status) -> {
                    List<MessageViewModel> messageViewModels = InboxRepository.this.messageViewModels.getValue();
                    for (PNHistoryItemResult messageItem : result.getMessages()) {
                        if (MessageViewModel.isConvertible(messageItem.getEntry().getAsJsonObject())) {
                            messageViewModels.add(new MessageViewModel(
                                    messageItem.getEntry().getAsJsonObject(), uuid));
                        }
                    }
                    InboxRepository.this.messageViewModels.postValue(messageViewModels);
                });
    }

    public void registerChannel(String channelName) {
        pubnub.subscribe().channels(Collections.singletonList(channelName)).execute();
    }

    public LiveData<List<MessageViewModel>> getMessageViewModels() {
        return messageViewModels;
    }

    public void clearChatHistory(String channelName) {
        pubnub.deleteMessages().channels(Collections.singletonList(channelName))
                .async((result, status) -> {
                    List<MessageViewModel> messageViewModels = InboxRepository.this.messageViewModels.getValue();
                    messageViewModels.clear();
                    InboxRepository.this.messageViewModels.postValue(messageViewModels);
                });
    }
}
