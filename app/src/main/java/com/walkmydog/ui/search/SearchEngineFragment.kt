package com.walkmydog.ui.search

import com.walkmydog.ui.search.SearchEngineViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import com.walkmydog.ui.search.SearchEngineFragment

class SearchEngineFragment : Fragment() {
    private var mViewModel: SearchEngineViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_engine_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(SearchEngineViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): SearchEngineFragment {
            return SearchEngineFragment()
        }
    }
}