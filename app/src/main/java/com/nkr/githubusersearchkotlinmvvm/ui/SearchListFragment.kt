package com.nkr.githubusersearchkotlinmvvm.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.nkr.githubusersearchkotlinmvvm.R
import com.nkr.githubusersearchkotlinmvvm.databinding.SearchListFragmentBinding
import com.nkr.githubusersearchkotlinmvvm.ui.Search.SearchEvent
import com.nkr.githubusersearchkotlinmvvm.ui.Search.buildlogic.SearchResultInjector

class SearchListFragment : Fragment() {

    companion object {
        fun newInstance() = SearchListFragment()
    }

    private lateinit var viewModel: SearchListViewModel
    private lateinit var binding: SearchListFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.search_list_fragment, container, false)

        return binding.root

    }


    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProvider(
            this,
            SearchResultInjector(requireActivity().application).provideSearchViewModelFactory()
        ).get(SearchListViewModel::class.java)


        binding.viewModel= viewModel
        binding.lifecycleOwner = this


        // setupAdapter()
        setupListener()
        observeViewModel()



    }


    private fun setupAdapter() {

        viewModel.searchUserResultList.observe(
            viewLifecycleOwner,
            Observer { userList ->
                viewModel.searchAdapter.submitList(userList)

            }
        )


    }




    private fun setupListener() {

        binding.layoutToolbar?.etSearch?.setOnEditorActionListener() {
        v, actionId, event ->

        if (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.action == KeyEvent.ACTION_DOWN
                && event.keyCode == KeyEvent.KEYCODE_ENTER) {

            //fetch the data and populate recyclerview
           // queryString = binding.layoutToolbar.etSearch.text.toString()
           var queryString = viewModel.userQuery.value.toString()


          //  Log.d(TAG,"user_name"+viewModel.userQuery.value.toString())

            if(!queryString.isNullOrEmpty() ) {

                Toast.makeText(requireContext(),"Loading. Please wait...",Toast.LENGTH_LONG).show()
                viewModel.handleEvent(SearchEvent.OnSearchQueryComplete(queryString))

                setupAdapter()


            }

            true
        }
        // Return true if you have consumed the action, else false.
        false
    }



    }

    private fun observeViewModel() {

        viewModel.showEmptyUser.observe(this, Observer { aBoolean ->
            if (aBoolean) {
                binding.tvEmptyUser.visibility = View.VISIBLE
            }else{
                binding.tvEmptyUser.visibility = View.GONE
            }
        })

        viewModel.error.observe(this, Observer { errorMsg ->
            Toast.makeText(requireContext(),errorMsg,Toast.LENGTH_LONG).show()
        })



    }


    override fun onDestroyView() {
        super.onDestroyView()

       binding.rvSavedSearch.adapter = null

    }

}
