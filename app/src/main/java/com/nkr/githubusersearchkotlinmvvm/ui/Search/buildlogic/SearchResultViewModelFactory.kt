package com.nkr.githubusersearchkotlinmvvm.ui.Search.buildlogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nkr.githubusersearchkotlinmvvm.repository.ISearchRepository
import com.nkr.githubusersearchkotlinmvvm.ui.SearchListViewModel

class SearchResultViewModelFactory(val searchRepo : ISearchRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return SearchListViewModel(
            searchRepo
        ) as T
    }


}