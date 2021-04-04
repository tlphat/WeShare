package com.hcmus.android.weshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.databinding.ChatUserBinding;

import java.util.List;

public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.MyAdapter>{

    List<ChatUserViewModel> data;
    Context context;
    private LayoutInflater inflater;

    public ChatUserAdapter(List<ChatUserViewModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater=LayoutInflater.from(parent.getContext());
        }
        ChatUserBinding chatUserBinding = ChatUserBinding.inflate(inflater, parent, false);
        return new MyAdapter(chatUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatUserAdapter.MyAdapter holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        private ChatUserBinding chatUserBinding;


        public MyAdapter(ChatUserBinding chatUserBinding) {
            super(chatUserBinding.getRoot());
            this.chatUserBinding = chatUserBinding;
        }

        public void bind(ChatUserViewModel chatUserViewModel) {
            this.chatUserBinding.setViewModel(chatUserViewModel);
        }

        public ChatUserBinding getChatUserBinding() {
            return chatUserBinding;
        }
    }
}
