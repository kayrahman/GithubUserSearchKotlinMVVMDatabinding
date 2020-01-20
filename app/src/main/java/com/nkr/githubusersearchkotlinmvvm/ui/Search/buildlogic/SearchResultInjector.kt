package com.nkr.githubusersearchkotlinmvvm.ui.Search.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nkr.githubusersearchkotlinmvvm.repository.ISearchRepository
import com.nkr.githubusersearchkotlinmvvm.repository.SearchRepo

class SearchResultInjector(application: Application) : AndroidViewModel(application){

    private fun getSearchRepo():ISearchRepository {
        return SearchRepo()

    }

    fun provideSearchViewModelFactory(): SearchResultViewModelFactory =
        SearchResultViewModelFactory(
            getSearchRepo()
        )

}