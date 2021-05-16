package com.hcmus.android.weshare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.databinding.ContactBinding;
import com.hcmus.android.weshare.databinding.SearchBinding;
import com.hcmus.android.weshare.viewmodel.ContactViewModel;
import com.hcmus.android.weshare.viewmodel.SearchItemViewModel;

import java.util.List;

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.MyAdapter> {

    private List<SearchItemViewModel> data;
    private LayoutInflater inflater;
    private Context context;

    public SearchResultListAdapter(List<SearchItemViewModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        SearchBinding searchBinding = SearchBinding.inflate(inflater, parent, false);
        return new MyAdapter(searchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultListAdapter.MyAdapter holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<SearchItemViewModel> data) {
        this.data = data;
    }

    public class MyAdapter extends RecyclerView.ViewHolder {

        private final SearchBinding searchBinding;
        private Button addFriendButton;

        public MyAdapter(SearchBinding searchBinding) {
            super(searchBinding.getRoot());
            this.searchBinding = searchBinding;
        }


        public void bind(SearchItemViewModel searchItemViewModel) {
            this.searchBinding.setViewModel(searchItemViewModel);
        }

        public SearchBinding getSearchBinding() {
            return searchBinding;
        }

    }
}
