package com.walkmydog.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.walkmydog.MainActivity
import com.walkmydog.R

class LoginActivity : AppCompatActivity() {

    private lateinit var mEmailLogin: EditText
    private lateinit var mPasswordLogin: EditText
    private lateinit var mRegisterBtnActivity: TextView
    private lateinit var mLoginBtn: Button
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mEmailLogin = findViewById(R.id.editTextEmailLogin)
        mPasswordLogin = findViewById(R.id.editTextPasswordLogin)
        mRegisterBtnActivity = findViewById(R.id.textViewCreateAccount)
        mLoginBtn = findViewById(R.id.LoginButton)

        fAuth = FirebaseAuth.getInstance()

        //check authentication
        if(fAuth.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        mLoginBtn.setOnClickListener {

            val email: String = mEmailLogin.text.toString().trim()
            val password: String = mPasswordLogin.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                mEmailLogin.error = "Email is required !"
            }

            if (TextUtils.isEmpty(password)) {
                mPasswordLogin.error = "Password is required !"
            }

            if (password.length < 8) {
                mPasswordLogin.error = "Password must be at 8 chars or longer !"
            }

            //login to Firebase
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "User Signed.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Error ! " + it.exception, Toast.LENGTH_SHORT).show()
                }
            }
        }

        mRegisterBtnActivity.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }
}