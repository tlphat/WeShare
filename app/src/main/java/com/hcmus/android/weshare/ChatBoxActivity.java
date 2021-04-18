package com.hcmus.android.weshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.adapter.MessageListAdapter;
import com.hcmus.android.weshare.model.User;
import com.hcmus.android.weshare.viewmodel.InboxViewModel;
import com.hcmus.android.weshare.viewmodel.MessageViewModel;

import java.util.List;

public class ChatBoxActivity extends AppCompatActivity {

    private InboxViewModel inboxViewModel;
    private EditText messageEditText;

    private MessageListAdapter messageListAdapter;
    private User fromUser;
    private User toUser;
    private String channelName;
    private LiveData<List<MessageViewModel>> messages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbox);
        initChannelInfo();
        initViewModel();
        handleMessageListFromViewModel();
        initComponents();
        fetchMessages();
    }

    private void fetchMessages() {
        inboxViewModel.fetchMessages(channelName);
    }

    private void initViewModel() {
        inboxViewModel = new ViewModelProvider(this).get(InboxViewModel.class);
        inboxViewModel.initRepository(fromUser.getId());
        inboxViewModel.registerChannel(channelName);
    }

    private void initChannelInfo() {
        Intent intent = getIntent();
        fromUser = intent.getParcelableExtra("from_user");
        toUser = intent.getParcelableExtra("to_user");
        channelName = intent.getStringExtra("channel_id");
    }

    private void handleMessageListFromViewModel() {
        messages = inboxViewModel.getMessageViewModels();
        messages.observe(ChatBoxActivity.this, messageViewModels ->
                messageListAdapter.notifyDataSetChanged());
    }

    private void initComponents() {
        messageEditText = findViewById(R.id.input_text);
        RecyclerView chatBox = findViewById(R.id.chatbox_layout);
        chatBox.setLayoutManager(new LinearLayoutManager(this));
        messageListAdapter = new MessageListAdapter(messages.getValue(), this);
        chatBox.setAdapter(messageListAdapter);
        setReceiverTextName();
    }

    private void setReceiverTextName() {
        TextView receiverName = findViewById(R.id.chatbox_name);
        if (toUser.getDisplayName() != null) {
            receiverName.setText(toUser.getDisplayName());
        } else {
            receiverName.setText(toUser.getEmail());
        }
    }

    public void sendMessageButtonClick(View view) {
        if (fromUser.getDisplayName() != null) {
            inboxViewModel.sendMessage(fromUser.getDisplayName(),
                    messageEditText.getText().toString(), channelName);
        } else {
            inboxViewModel.sendMessage(fromUser.getEmail(),
                    messageEditText.getText().toString(), channelName);
        }
        disableEditTextFocus();
    }

    private void disableEditTextFocus() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        messageEditText.setText("");
    }
}
