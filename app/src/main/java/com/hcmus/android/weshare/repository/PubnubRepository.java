package com.hcmus.android.weshare.repository;

import android.content.Context;

import com.google.gson.JsonObject;
import com.hcmus.android.weshare.R;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import java.util.Collections;

public abstract class PubnubRepository {

    protected final String uuid;
    protected final Context context;
    protected PubNub pubnub;

    public PubnubRepository(Context context, String uuid) {
        this.context = context;
        this.uuid = uuid;
        PNConfiguration config = initPubNubConfig();
        initPubNub(config);
    }

    private void initPubNub(PNConfiguration config) {
        pubnub = new PubNub(config);
        handlePubnubMessage();
    }

    protected abstract void handlePubnubMessage();

    private PNConfiguration initPubNubConfig() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(context.getString(R.string.pubnub_subscribe_key));
        pnConfiguration.setPublishKey(context.getString(R.string.pubnub_publish_key));
        pnConfiguration.setUuid(uuid);
        return pnConfiguration;
    }

    public void registerChannel(String channelName) {
        pubnub.subscribe().channels(Collections.singletonList(channelName)).execute();
    }

    public void sendMessage(JsonObject msg, String channelName) {
        pubnub.publish()
                .message(msg)
                .channel(channelName)
                .async((result, status) -> {
                });
    }
}
