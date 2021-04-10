package com.hcmus.android.weshare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.databinding.ContactBinding;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyAdapter> {
    private final Context context;
    private final List<ContactViewModel> data;
    private LayoutInflater inflater;

    public ContactListAdapter(List<ContactViewModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ContactBinding contactBinding = ContactBinding.inflate(inflater, parent, false);
        return new ContactListAdapter.MyAdapter(contactBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyAdapter holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyAdapter extends RecyclerView.ViewHolder {

        private final ContactBinding contactBinding;


        public MyAdapter(ContactBinding contactBinding) {
            super(contactBinding.getRoot());
            this.contactBinding = contactBinding;
        }

        public void bind(ContactViewModel contactViewModel) {
            this.contactBinding.setViewModel(contactViewModel);
        }

        public ContactBinding getContactBinding() {
            return contactBinding;
        }
    }
}
