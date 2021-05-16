package com.hcmus.android.weshare;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmus.android.weshare.adapter.SearchResultListAdapter;
import com.hcmus.android.weshare.viewmodel.SearchItemViewModel;
import com.hcmus.android.weshare.viewmodel.SearchResultListViewModel;

import java.util.List;

public class AddFriendActivity extends AppCompatActivity implements SearchResultListAdapter.OnItemClick {

    private final String TAG = getClass().getSimpleName();

    private SearchView friendSearchView;
    private SearchResultListViewModel searchResultListViewModel;
    private SearchResultListAdapter searchResultListAdapter;
    private LiveData<List<SearchItemViewModel>> searchItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        initViewModel();
        handleListFromViewModel();
        initComponents();
    }

    private void initViewModel() {
        friendSearchView = findViewById(R.id.search_friend_bar);
        searchResultListViewModel = new ViewModelProvider(this).get(SearchResultListViewModel.class);
        searchResultListViewModel.initRepository();
    }

    private void initComponents() {

        friendSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                retrieveUsers(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        final RecyclerView resultList = findViewById(R.id.search_friend_list_layout);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        searchResultListAdapter = new SearchResultListAdapter(searchItems.getValue(), this);
        resultList.setAdapter(searchResultListAdapter);
    }

    private void handleListFromViewModel() {
        searchItems = searchResultListViewModel.getSearchItemViewModels();
        searchItems.observe(AddFriendActivity.this, searchItemViewModels -> {
            searchResultListAdapter.updateData(searchItemViewModels);
            searchResultListAdapter.notifyDataSetChanged();
        });
    }

    private void retrieveUsers(String name) {
        searchResultListViewModel.retrieveUsers(name);
    }

    @Override
    public void onContactItemClick(SearchItemViewModel searchItem) {
        Log.e(TAG, searchItem.getUser().getEmail());
    }
}