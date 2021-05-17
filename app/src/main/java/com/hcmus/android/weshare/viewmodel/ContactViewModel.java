package com.hcmus.android.weshare.viewmodel;

import com.hcmus.android.weshare.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactViewModel {
    private String connectionID = "dummy";
    private User user;
}
