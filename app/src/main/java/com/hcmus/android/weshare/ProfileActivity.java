package com.hcmus.android.weshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.hcmus.android.weshare.databinding.ActivityProfileBinding;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.viewmodel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 24;
    private User user;
    private final String TAG = getClass().getSimpleName();
    private LiveData<User> liveUser;
    private ActivityProfileBinding activityProfileBinding;
    private ProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initUserInfo();
        initComponents();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            assert data != null;
            User tmp = data.getParcelableExtra("user");
            activityProfileBinding.setUser(tmp);
            viewModel.updateUser(tmp.getId(), tmp);
        }
    }

    private void initComponents() {
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityProfileBinding.setUser(user);
        boolean isCurrentUserProfile = getIntent().getBooleanExtra("is_current", false);
        if (!isCurrentUserProfile) {
            Button editButton = findViewById(R.id.edit_button);
            editButton.setEnabled(false);
        }
    }

    private void initUserInfo() {
        final Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        liveUser = viewModel.getUser();
        liveUser.observe(this, tmpUser -> {
            activityProfileBinding.setUser(liveUser.getValue());
        });
        viewModel.loadUser(user.getId());
    }

    public void editProfileButtonClick(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, REQUEST_CODE);
    }
}