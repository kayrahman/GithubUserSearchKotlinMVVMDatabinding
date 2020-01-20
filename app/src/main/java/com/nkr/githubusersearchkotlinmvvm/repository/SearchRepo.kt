package com.nkr.githubusersearchkotlinmvvm.repository

import com.nkr.githubusersearchkotlinmvvm.base.BaseRepo
import com.nkr.githubusersearchkotlinmvvm.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchRepo : BaseRepo(),ISearchRepository {

    override fun getUserSearchList(q:String,page_num:Int,requestStatusCallBack : RequestStatusCallBack) {
        val networkApi = NetworkModule.networkApi

        networkApi.searchUser(q,page_num)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { requestStatusCallBack.startRequest() }
            .doOnTerminate { requestStatusCallBack.endRequest() }
            .subscribe(
                { result -> requestStatusCallBack.resultSuccess(result) },
                { t -> requestStatusCallBack.resultError(t) }

            )

    }


}