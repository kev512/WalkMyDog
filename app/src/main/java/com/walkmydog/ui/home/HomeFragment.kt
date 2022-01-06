package com.walkmydog.ui.home

import android.content.Intent
import com.walkmydog.ui.home.HomeViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.walkmydog.authentication.LoginActivity
import com.walkmydog.ui.home.HomeFragment

class HomeFragment : Fragment() {

    private var mViewModel: HomeViewModel? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.home_fragment, container, false)


            return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}