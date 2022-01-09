package com.walkmydog.ui.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.walkmydog.R
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.okhttp.Dispatcher
import com.walkmydog.authentication.LoginActivity
import com.walkmydog.data.User
import com.walkmydog.ui.profile.addpooch.AddPoochActivity
import com.walkmydog.ui.profile.changepassword.ChangePasswordActivity
import com.walkmydog.ui.profile.changepersonaldata.ChangePersonalDataActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class ProfileFragment : Fragment() {

    private var mViewModel: ProfileViewModel? = null

    private lateinit var fAuth: FirebaseAuth
    private val userCollectionRef = FirebaseFirestore.getInstance().collection("Users")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.profile_fragment, container, false)

        fAuth = FirebaseAuth.getInstance()

        //Logout button
        val buttonToLogout: Button = rootView.findViewById(R.id.logoutButton)
        buttonToLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this.context?.applicationContext, LoginActivity::class.java))
        }

        //Change personal data btn
        val changePersonalDataBtn: Button = rootView.findViewById(R.id.changePersonalDataBtnActivity)
        changePersonalDataBtn.setOnClickListener {
            startActivity(Intent(context, ChangePersonalDataActivity::class.java))
        }

        //change password activity
        val changePasswordActivityBtn: Button = rootView.findViewById(R.id.changePasswordBtnActivity)
        changePasswordActivityBtn.setOnClickListener {
            startActivity(Intent(context, ChangePasswordActivity::class.java))
        }

        //testing box
        val textBox: TextView = rootView.findViewById(R.id.testBoxForStrings)
        val UserId: String = fAuth.currentUser!!.uid
        textBox.setText(UserId)


        //Add pooch button
        val addPoochActivity: Button = rootView.findViewById(R.id.AddPoochBtnActivity)
        addPoochActivity.setOnClickListener {
            startActivity(Intent(context, AddPoochActivity::class.java))
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}