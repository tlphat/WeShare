package com.hcmus.android.weshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.hcmus.android.weshare.databinding.ActivityProfileBinding;
import com.hcmus.android.weshare.model.User;

public class ProfileActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUserInfo();
        initComponents();
    }

    private void initComponents() {
        ActivityProfileBinding activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityProfileBinding.setUser(user);
    }

    private void initUserInfo() {
        final Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
    }
}