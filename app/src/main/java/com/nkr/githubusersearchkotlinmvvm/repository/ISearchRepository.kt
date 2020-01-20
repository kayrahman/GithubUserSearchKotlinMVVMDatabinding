package com.nkr.githubusersearchkotlinmvvm.repository

import com.nkr.githubusersearchkotlinmvvm.base.BaseRepo

interface ISearchRepository {
    fun getUserSearchList(q:String,page_num:Int,requestStatusCallBack : BaseRepo.RequestStatusCallBack)
}