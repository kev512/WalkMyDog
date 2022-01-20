package com.walkmydog.viewpager.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.walkmydog.R
import kotlinx.android.synthetic.main.fragment_second_screen.view.*
import kotlinx.android.synthetic.main.fragment_second_screen.view.backPager2
import kotlinx.android.synthetic.main.fragment_third_screen.view.*
import android.app.Activity
import android.content.Context

import android.content.Intent
import com.walkmydog.MainActivity
import com.walkmydog.authentication.LoginMethod
import com.walkmydog.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_third_screen.*
import kotlinx.coroutines.Dispatchers.Main


class ThirdScreen : Fragment() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third_screen, container, false)


        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.backPager3.setOnClickListener{
            viewPager?.currentItem = 1
        }

        view.finishPager.setOnClickListener {
            val intent = Intent (getActivity(), LoginMethod::class.java)
            getActivity()?.startActivity(intent)
            onBoardingFinished()

        }
        return view
    }
    private  fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}