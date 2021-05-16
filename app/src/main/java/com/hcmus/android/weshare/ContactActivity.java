package com.hcmus.android.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.adapter.ContactListAdapter;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.viewmodel.ContactListViewModel;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.List;

public class ContactActivity extends AppCompatActivity implements ContactListAdapter.OnItemClick {

    private final String TAG = getClass().getSimpleName();

    private ContactListViewModel contactListViewModel;
    private ContactListAdapter contactListAdapter;
    private User user;
    private LiveData<List<ContactViewModel>> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initUserIDInfo();
        initViewModel();
        handleListFromViewModel();
        initComponents();
        retrieveFriends();
    }

    private void retrieveFriends() {
        contactListViewModel.retrieveFriends(user.getId());
    }

    private void handleListFromViewModel() {
        friendList = contactListViewModel.getContactViewModels();
        friendList.observe(ContactActivity.this, contactViewModels -> {
            contactListAdapter.updateData(contactViewModels);
            contactListAdapter.notifyDataSetChanged();
        });
    }

    private void initUserIDInfo() {
        final Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
    }

    private void initViewModel() {
        contactListViewModel = new ViewModelProvider(this).get(ContactListViewModel.class);
        contactListViewModel.initRepository();
    }

    private void initComponents() {
        final RecyclerView contactList = findViewById(R.id.contact_list_layout);
        contactList.setLayoutManager(new LinearLayoutManager(this));
        contactListAdapter = new ContactListAdapter(friendList.getValue(), this);
        contactList.setAdapter(contactListAdapter);
    }

    @Override
    public void onContactItemClick(int position) {
        Intent intent = new Intent(this, ChatBoxActivity.class);
        intent.putExtra("from_user", user);
        intent.putExtra("to_user", friendList.getValue().get(position).getUser());
        intent.putExtra("channel_id", "private_"
                + friendList.getValue().get(position).getConnectionID());
        startActivity(intent);
    }

    @Override
    public void onClickImage(User user) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void searchUserButtonClick(View view) {
        navigateToSearchUSer();
    }

    private void navigateToSearchUSer() {
        Intent intent = new Intent(this, AddFriendActivity.class);
        startActivity(intent);
    }
}