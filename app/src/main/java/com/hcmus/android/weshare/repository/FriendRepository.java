package com.hcmus.android.weshare.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
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

public class FriendRepository extends PubnubRepository {

    private final String TAG = getClass().getSimpleName();
    MutableLiveData<JsonObject> messageLiveData = new MutableLiveData<>();

    public FriendRepository(Context context, String uuid) {
        super(context, uuid);
    }

    public LiveData<JsonObject> getMessageLiveData() {
        return messageLiveData;
    }

    @Override
    protected void handlePubnubMessage() {
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void message(@Nullable PubNub pubnub, @Nullable PNMessageResult message) {
                if (message == null) {
                    Log.e(TAG, "Empty message received");
                    return;
                }
                JsonObject messagePackage = message.getMessage().getAsJsonObject();
                messageLiveData.postValue(messagePackage);
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
}
