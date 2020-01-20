package com.nkr.githubusersearchkotlinmvvm


// TODO: look out for fragment backstack
// TODO: look out for screen rotation
//TODO : handle network error -done
//TODO: clear list on empty text
//TODO:clear fragment adapter list on destroy view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nkr.githubusersearchkotlinmvvm.ui.SearchListFragment


private const val SEARCH_FRAGMENT = "search_fragment"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val view = supportFragmentManager.findFragmentByTag(SEARCH_FRAGMENT) as SearchListFragment?
            ?: SearchListFragment()



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_nav, view, SEARCH_FRAGMENT)
            .commitNowAllowingStateLoss()

    }

}
