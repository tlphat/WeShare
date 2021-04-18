package com.hcmus.android.weshare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.databinding.MessageBinding;
import com.hcmus.android.weshare.viewmodel.MessageViewModel;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyAdapter> {

    private final List<MessageViewModel> data;
    private final Context context;
    private LayoutInflater inflater;

    public MessageListAdapter(List<MessageViewModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        MessageBinding messageBinding = MessageBinding.inflate(inflater, parent, false);
        return new MyAdapter(messageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MyAdapter holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {

        private final MessageBinding chatUserBinding;


        public MyAdapter(MessageBinding chatUserBinding) {
            super(chatUserBinding.getRoot());
            this.chatUserBinding = chatUserBinding;
        }

        public void bind(MessageViewModel messageViewModel) {
            this.chatUserBinding.setViewModel(messageViewModel);
        }

        public MessageBinding getChatUserBinding() {
            return chatUserBinding;
        }

    }
}
