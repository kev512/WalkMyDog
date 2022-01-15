package com.walkmydog.viewpager.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.walkmydog.MainActivity
import com.walkmydog.R
import com.walkmydog.authentication.LoginActivity
import com.walkmydog.authentication.LoginMethod
import com.walkmydog.databinding.ActivityMain2Binding
import com.walkmydog.databinding.ActivityMainBinding


class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if(onBoardingFinished()){
                val intent = Intent (getActivity(), LoginMethod::class.java)
                getActivity()?.startActivity(intent)
            }
            else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            }
        }, 3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
    private  fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished",false)
    }

}