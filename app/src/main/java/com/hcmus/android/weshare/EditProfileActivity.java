package com.hcmus.android.weshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hcmus.android.weshare.databinding.ActivityEditProfileBinding;
import com.hcmus.android.weshare.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initUser();
        initComponents();
    }

    private void initComponents() {
        ActivityEditProfileBinding activityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        activityEditProfileBinding.setUser(user);
    }

    private void initUser() {
        user = getIntent().getParcelableExtra("user");
    }

    public void confirmChangedButton(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("user", user);
        setResult(Activity.RESULT_OK, returnIntent);
        Log.e(TAG, user.getDisplayName());
        finish();
    }
}
