package com.hcmus.android.weshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.adapter.ContactListAdapter;
import com.hcmus.android.weshare.viewmodel.ContactListViewModel;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;
import com.hcmus.android.weshare.viewmodel.InboxViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView contactList;
    private ContactListViewModel contactListViewModel;
    private ContactListAdapter contactListAdapter;
    private FirebaseUser user;
    private LiveData<List<ContactViewModel>> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ContactActi: ", "started");
        setContentView(R.layout.activity_contact);
        initUserIDInfo();
        initViewModel();
        handleListFromViewModel();
        initComponents();
    }

    private void handleListFromViewModel() {
        friendList = contactListViewModel.getContactViewModels();
        friendList.observe(ContactActivity.this, contactViewModels ->
                contactListAdapter.notifyDataSetChanged());
    }

    private void initUserIDInfo() {
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
    }

    private void initViewModel() {
        contactListViewModel = new ViewModelProvider(this).get(ContactListViewModel.class);
        Log.d("ContactlistViewmodel", "hello");
        contactListViewModel.initContactListRepository(user.getUid());

    }

    private void initComponents() {
        contactList = findViewById(R.id.contact_list_layout);
        contactList.setLayoutManager(new LinearLayoutManager(this));
        contactListAdapter = new ContactListAdapter(friendList.getValue(), this);
        contactList.setAdapter(contactListAdapter);
    }
}