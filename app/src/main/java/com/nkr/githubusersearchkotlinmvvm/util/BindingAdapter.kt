package com.nkr.githubusersearchkotlinmvvm.util

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper


@BindingAdapter(value = ["bindingAdapter", "defaultRvLayout", "showDefaultDividerLine"], requireAll = false)
fun setBindingAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>, defaultLayout: Boolean?, showDefaultDividerLine: Boolean?) {
    if (defaultLayout != null && defaultLayout) {
        val mLayoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        view.layoutManager = mLayoutManager
        view.itemAnimator = DefaultItemAnimator()
        if (showDefaultDividerLine == true) {
            view.addItemDecoration(
                DividerItemDecoration(view.context, LinearLayout.VERTICAL)
            )
        }
        // Vertical
        OverScrollDecoratorHelper.setUpOverScroll(view, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

    }
    view.adapter = adapter
}