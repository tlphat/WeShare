package com.hcmus.android.weshare.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Channel {
    private final int id;
    private final String ownerId;
    private final String personId;
}
