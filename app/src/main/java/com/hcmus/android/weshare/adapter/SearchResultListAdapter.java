package com.hcmus.android.weshare.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.databinding.SearchBinding;
import com.hcmus.android.weshare.viewmodel.SearchItemViewModel;

import java.util.List;

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.MyAdapter> {

    private List<SearchItemViewModel> data;
    private LayoutInflater inflater;
    private final OnItemClick itemClick;

    public SearchResultListAdapter(List<SearchItemViewModel> data, OnItemClick itemClick) {
        this.data = data;
        this.itemClick = itemClick;
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

    public interface OnItemClick {
        void onContactItemClick(SearchItemViewModel searchItem);
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

        public MyAdapter(SearchBinding searchBinding) {
            super(searchBinding.getRoot());
            this.searchBinding = searchBinding;
            this.searchBinding.addFriendButton.setOnClickListener(v -> itemClick
                    .onContactItemClick(this.searchBinding.getViewModel()));
        }

        public void bind(SearchItemViewModel searchItemViewModel) {
            this.searchBinding.setViewModel(searchItemViewModel);
        }

        public SearchBinding getSearchBinding() {
            return searchBinding;
        }
    }
}
