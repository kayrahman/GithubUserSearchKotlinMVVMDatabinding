package com.nkr.githubusersearchkotlinmvvm.datasource


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.nkr.githubusersearchkotlinmvvm.model.User
import com.nkr.githubusersearchkotlinmvvm.repository.ISearchRepository
import com.nkr.githubusersearchkotlinmvvm.repository.SearchRepo
import com.nkr.githubusersearchkotlinmvvm.ui.EventState


class SearchUserDataSourceFactory(val user_query:String,val repo : ISearchRepository , val onAction: (event : EventState) -> Unit) : DataSource.Factory<Int,User>() {

    private var searchUserResultDataSource: MutableLiveData<PageKeyedDataSource<Int, User>> = MutableLiveData()
    val searchUserResultLiveDatasource: MutableLiveData<PageKeyedDataSource<Int, User>> get() = searchUserResultDataSource



    override fun create(): SearchUserDataSource {
        val dataSource = SearchUserDataSource(user_query,repo,onAction)
        searchUserResultDataSource.postValue(dataSource)
        return dataSource

    }


}