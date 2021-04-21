package com.hcmus.android.weshare.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private String email;
    private String displayName;
    private String dob;
    private String gender;
    private String id;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
        this.displayName = email;
    }

    protected User(Parcel in) {
        id = in.readString();
        email = in.readString();
        displayName = in.readString();
        dob = in.readString();
        gender = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(email);
        parcel.writeString(displayName);
        parcel.writeString(dob);
        parcel.writeString(gender);
    }
}
