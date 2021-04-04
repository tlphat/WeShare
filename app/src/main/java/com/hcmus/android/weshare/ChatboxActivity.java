package com.hcmus.android.weshare;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatboxActivity extends AppCompatActivity {
    RecyclerView chatbox;
    List<ChatUserViewModel> data = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_chatbox);

        chatbox.findViewById(R.id.chatbox_layout);
        chatbox.setLayoutManager(new LinearLayoutManager(this));;

        ChatUserViewModel chatUserViewModel1 = new ChatUserViewModel();
        chatUserViewModel1.email = "@tlp";
        chatUserViewModel1.message = "hi!";
        data.add(chatUserViewModel1);

        ChatUserViewModel chatUserViewModel2 = new ChatUserViewModel();
        chatUserViewModel1.email = "@tlp2";
        chatUserViewModel1.message = "ok!";
        data.add(chatUserViewModel2);

        ChatUserAdapter chatUserAdapter = new ChatUserAdapter(data, this);
        chatbox.setAdapter(chatUserAdapter);
    }
}
