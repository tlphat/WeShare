package com.hcmus.android.weshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.hcmus.android.weshare.adapter.ContactListAdapter;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView contactList;
    private ContactListAdapter contactListAdapter;
    private List<ContactViewModel> friendList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initFriendList();
        initComponents();
    }

    private void initFriendList() {

    }

    private void initComponents() {
        contactList = findViewById(R.id.contact_list_layout);
        contactList.setLayoutManager(new LinearLayoutManager(this));
        contactListAdapter = new ContactListAdapter(friendList, this);
        contactList.setAdapter(contactListAdapter);
    }
}