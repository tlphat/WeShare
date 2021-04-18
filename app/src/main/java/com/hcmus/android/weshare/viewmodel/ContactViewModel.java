package com.hcmus.android.weshare.viewmodel;

import com.hcmus.android.weshare.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContactViewModel {
    private final String connectionID = "dummy";
    private final User user;
}
