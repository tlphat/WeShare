package com.hcmus.android.weshare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hcmus.android.weshare.repository.ContactListRepository;
import com.hcmus.android.weshare.repository.SearchResultRepository;

import java.util.List;

public class SearchResultListViewModel extends AndroidViewModel {
    private SearchResultRepository searchResultRepository;
    private LiveData<List<SearchItemViewModel>> searchItemViewModels;

    public SearchResultListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRepository() {
        searchResultRepository = new SearchResultRepository(getApplication());
        searchItemViewModels = searchResultRepository.getSearchItemViewModels();
    }

    public LiveData<List<SearchItemViewModel>> getSearchItemViewModels() {
        return searchItemViewModels;
    }

    public void retrieveUsers(String name) {
        searchResultRepository.retrieveUsers(name);
    }
}