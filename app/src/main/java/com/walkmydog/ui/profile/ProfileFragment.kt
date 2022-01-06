package com.walkmydog.ui.profile

import android.content.Intent
import com.walkmydog.ui.profile.ProfileViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.walkmydog.authentication.LoginActivity
import com.walkmydog.ui.profile.ProfileFragment

class ProfileFragment : Fragment() {

    private var mViewModel: ProfileViewModel? = null

    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.profile_fragment, container, false)

        fAuth = FirebaseAuth.getInstance()

        //testing logout button
        val buttonToLogout: Button = rootView.findViewById(R.id.logoutButton)
        buttonToLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this.context?.applicationContext, LoginActivity::class.java))
        }

        //testing box
        val textBox: TextView = rootView.findViewById(R.id.testBoxForStrings)
        val UserId: String = fAuth.currentUser!!.uid
        textBox.setText(UserId)



        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}