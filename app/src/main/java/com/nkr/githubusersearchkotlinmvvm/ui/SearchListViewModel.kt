package com.nkr.githubusersearchkotlinmvvm.ui


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.nkr.githubusersearchkotlinmvvm.adapter.SearchListAdapter
import com.nkr.githubusersearchkotlinmvvm.base.BaseViewModel
import com.nkr.githubusersearchkotlinmvvm.datasource.SearchUserDataSourceFactory
import com.nkr.githubusersearchkotlinmvvm.model.User
import com.nkr.githubusersearchkotlinmvvm.repository.ISearchRepository
import com.nkr.githubusersearchkotlinmvvm.ui.Search.SearchEvent

class SearchListViewModel(val searchRepo: ISearchRepository) : BaseViewModel<SearchEvent>() {

    var searchAdapter = SearchListAdapter()

    lateinit var searchUserResultList : LiveData<PagedList<User>>
    lateinit var searchUserResultLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, User>>

    var showEmptyUser = MutableLiveData<Boolean>()
    var userQuery = MutableLiveData<String>().default("")


    override fun handleEvent(event: SearchEvent) {

        when(event){
            is SearchEvent.OnSearchQueryComplete ->{

                showEmptyUser.value = false
                populateSearchListAdapter(event.query)

            }
        }


    }

    private fun populateSearchListAdapter(query: String) {


        var factory = SearchUserDataSourceFactory(query,searchRepo){state ->

            when(state){

                is EventState.onEmptyList -> showEmptyUser.value = true
                is EventState.onError<*> -> {
                    errorMsg.value = getErrorMsg(state.error)

                }

            }
        }

        searchUserResultLiveDataSource = factory.searchUserResultLiveDatasource

        searchUserResultList = LivePagedListBuilder(factory,
            PagedList.Config.Builder()
                .setPageSize(50)
                .setEnablePlaceholders(true)
                .build()
        ).build() as LiveData<PagedList<User>>



        Log.d("userlist_size",searchUserResultList.value?.size.toString())


    }



}
