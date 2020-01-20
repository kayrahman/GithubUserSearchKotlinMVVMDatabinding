package com.nkr.githubusersearchkotlinmvvm.network

import com.nkr.githubusersearchkotlinmvvm.model.GithubSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {


    @GET("/search/users")
    fun searchUser(@Query("q") query : String,
                   @Query("page") page_num : Int

    ): Observable<GithubSearchResponse>



}