package com.nkr.githubusersearchkotlinmvvm.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nkr.githubusersearchkotlinmvvm.model.User

class SearchDiffUtilCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.avatar_url == newItem.avatar_url
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.avatar_url == newItem.avatar_url
    }
}