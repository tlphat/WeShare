package com.hcmus.android.weshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hcmus.android.weshare.adapter.ContactListAdapter;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    RecyclerView contactList;
    ContactListAdapter contactListAdapter;
    List<ContactViewModel> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initFriendList();
        initComponents();
    }

    private void initFriendList() {
        friendList = new ArrayList<ContactViewModel>();
        friendList.add(new ContactViewModel("tlphat18@apcs.vn"));
        friendList.add(new ContactViewModel("12345678910@apcs.vn"));
        friendList.add(new ContactViewModel("hmmmmmm@apcs.vn"));
        friendList.add(new ContactViewModel("lalala123@apcs.vn"));
        friendList.add(new ContactViewModel("dungplt242@apcs.vn"));
        friendList.add(new ContactViewModel("test@apcs.vn"));
        friendList.add(new ContactViewModel("mytest@apcs.vn"));
        friendList.add(new ContactViewModel("abcxyz@apcs.vn"));
        friendList.add(new ContactViewModel("abcxyz@apcs.vn"));

    }

    private void initComponents() {
        contactList = findViewById(R.id.contact_list_layout);
        contactListAdapter = new ContactListAdapter(friendList, this);
        contactList.setAdapter(contactListAdapter);
    }


}