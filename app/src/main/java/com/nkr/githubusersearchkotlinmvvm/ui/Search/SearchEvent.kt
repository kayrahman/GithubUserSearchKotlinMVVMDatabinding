package com.nkr.githubusersearchkotlinmvvm.ui.Search

sealed class SearchEvent{
    data class OnSearchQueryComplete(val query : String) : SearchEvent()
}