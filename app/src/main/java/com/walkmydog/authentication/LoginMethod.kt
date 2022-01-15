package com.walkmydog.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.walkmydog.MainActivity
import com.walkmydog.R

class LoginMethod : AppCompatActivity() {

    private lateinit var mSignin: Button
    private lateinit var mFacebook: Button
    private lateinit var mGoogle: Button
    private lateinit var mRegister: TextView
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_method)

        mSignin = findViewById(R.id.LoginButton)
        mFacebook = findViewById(R.id.facebookLogin)
        mGoogle = findViewById(R.id.googleLogin)
        mRegister = findViewById(R.id.textViewCreateAccount)
        fAuth = FirebaseAuth.getInstance()

        if(fAuth.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        mSignin.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        mRegister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))

        }

    }
}