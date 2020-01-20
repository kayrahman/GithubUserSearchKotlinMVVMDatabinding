package com.nkr.githubusersearchkotlinmvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Placeholder
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nkr.githubusersearchkotlinmvvm.R
import com.nkr.githubusersearchkotlinmvvm.model.User
import kotlinx.android.synthetic.main.list_item_user_info.view.*

class SearchListAdapter : PagedListAdapter<User, SearchListAdapter.UserViewHolder>(SearchDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return UserViewHolder(
            inflater.inflate(R.layout.list_item_user_info, parent, false)
        )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position).let { user ->
            holder.username.text = user!!.login

            Glide.with(holder.itemView.context)
                .load(user.avatar_url)
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.ic_user)
                )

                .into(holder.profImage)

            Log.d("username",user.login)

        }

    }


    class UserViewHolder(root:View): RecyclerView.ViewHolder(root){
        var username: TextView = root.tv_list_item_user_info
        var profImage: ImageView = root.iv_list_item_user_info

    }
}