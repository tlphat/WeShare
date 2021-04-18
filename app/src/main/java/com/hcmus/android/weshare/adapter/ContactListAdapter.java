package com.hcmus.android.weshare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.databinding.ContactBinding;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyAdapter> {

    private List<ContactViewModel> data;
    private LayoutInflater inflater;
    private final OnItemClick itemClick;

    public ContactListAdapter(List<ContactViewModel> data, OnItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ContactBinding contactBinding = ContactBinding.inflate(inflater, parent, false);
        return new MyAdapter(contactBinding, itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyAdapter holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<ContactViewModel> data) {
        this.data = data;
    }

    public interface OnItemClick {
        void onContactItemClick(int position);
    }

    public class MyAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ContactBinding contactBinding;
        private final OnItemClick itemClick;

        public MyAdapter(ContactBinding contactBinding, OnItemClick itemClick) {
            super(contactBinding.getRoot());
            this.contactBinding = contactBinding;
            this.itemClick = itemClick;
            contactBinding.contactRow.setOnClickListener(this);
        }

        public void bind(ContactViewModel contactViewModel) {
            this.contactBinding.setViewModel(contactViewModel);
        }

        public ContactBinding getContactBinding() {
            return contactBinding;
        }

        @Override
        public void onClick(View view) {
            itemClick.onContactItemClick(getAdapterPosition());
        }
    }
}
