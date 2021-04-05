package com.hcmus.android.weshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.adapter.MessageListAdapter;
import com.hcmus.android.weshare.viewmodel.InboxViewModel;
import com.hcmus.android.weshare.viewmodel.MessageViewModel;

import java.util.List;

public class ChatBoxActivity extends AppCompatActivity {

    private RecyclerView chatBox;
    private InboxViewModel inboxViewModel;
    private EditText messageEditText;

    private MessageListAdapter messageListAdapter;
    private FirebaseUser user;
    private LiveData<List<MessageViewModel>> messages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbox);
        initUserIDInfo();
        initViewModel();
        handleMessageListFromViewModel();
        initComponents();
    }

    private void initViewModel() {
        inboxViewModel = new ViewModelProvider(this).get(InboxViewModel.class);
        inboxViewModel.initRepository(user.getUid());
        inboxViewModel.registerChannel("test_channel");
    }

    private void initUserIDInfo() {
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
    }

    private void handleMessageListFromViewModel() {
        messages = inboxViewModel.getMessageViewModels();
        messages.observe(ChatBoxActivity.this, messageViewModels ->
                messageListAdapter.notifyDataSetChanged());
    }

    private void initComponents() {
        messageEditText = findViewById(R.id.input_text);
        chatBox = findViewById(R.id.chatbox_layout);
        chatBox.setLayoutManager(new LinearLayoutManager(this));
        messageListAdapter = new MessageListAdapter(messages.getValue(), this);
        chatBox.setAdapter(messageListAdapter);
    }

    public void sendMessageButtonClick(View view) {
        inboxViewModel.sendMessage(user.getEmail(), messageEditText.getText().toString());
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
