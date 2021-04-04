package com.hcmus.android.weshare;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.adapter.MessageListAdapter;
import com.hcmus.android.weshare.model.Message;
import com.hcmus.android.weshare.viewmodel.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatBoxActivity extends AppCompatActivity {

    private RecyclerView chatBox;
    private final List<MessageViewModel> data = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbox);
        initComponents();
        setData();
    }

    private void setData() {
        MessageViewModel messageViewModel1 = new MessageViewModel(new Message("@tlp", "hi!"));
        data.add(messageViewModel1);
        MessageViewModel messageViewModel2 = new MessageViewModel(new Message("@tlp2", "ok!"));
        data.add(messageViewModel2);
    }

    private void initComponents() {
        chatBox = findViewById(R.id.chatbox_layout);
        chatBox.setLayoutManager(new LinearLayoutManager(this));
        MessageListAdapter messageListAdapter = new MessageListAdapter(data, this);
        chatBox.setAdapter(messageListAdapter);
    }
}
