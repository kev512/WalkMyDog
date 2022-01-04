package com.walkmydog.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.walkmydog.R

class RegisterActivity : AppCompatActivity() {

   lateinit var mPersonNameRegister: EditText
   lateinit var mEmailRegister: EditText
   lateinit var mPasswordRegister: EditText
   lateinit var mPhoneNumberRegister: EditText
   lateinit var mLoginBtnActivity: TextView
   lateinit var mRegisterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPersonNameRegister = findViewById(R.id.editTextPersonNameRegister)
        mEmailRegister = findViewById(R.id.editTextEmailRegister)
        mPasswordRegister = findViewById(R.id.editTextPasswordRegister)
        mPhoneNumberRegister = findViewById(R.id.editTextPhoneRegister)
        mLoginBtnActivity = findViewById(R.id.loginTextViewInRegisterActivity)
        mRegisterBtn = findViewById(R.id.registerButton)
    }
}