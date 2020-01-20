package com.nkr.githubusersearchkotlinmvvm.model

data class GithubSearchResponse(val total_count : Int,
                                val incomplete_results : String,
                                val items : MutableList<User>
                                )