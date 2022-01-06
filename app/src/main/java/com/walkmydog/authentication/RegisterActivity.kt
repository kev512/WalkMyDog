package com.walkmydog.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.walkmydog.MainActivity
import com.walkmydog.R
import com.walkmydog.data.Gender
import com.walkmydog.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

   private lateinit var mPersonNameRegister: EditText
   private lateinit var mEmailRegister: EditText
   private lateinit var mPasswordRegister: EditText
   private lateinit var mPhoneNumberRegister: EditText
   private lateinit var mLoginBtnActivity: TextView
   private lateinit var mRegisterBtn: Button

   private val fAuth: FirebaseAuth = FirebaseAuth.getInstance()
   private val userCollectionRef = FirebaseFirestore.getInstance().collection("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPersonNameRegister = findViewById(R.id.editTextPersonNameRegister)
        mEmailRegister = findViewById(R.id.editTextEmailRegister)
        mPasswordRegister = findViewById(R.id.editTextPasswordRegister)
        mPhoneNumberRegister = findViewById(R.id.editTextPhoneRegister)
        mLoginBtnActivity = findViewById(R.id.loginTextViewInRegisterActivity)
        mRegisterBtn = findViewById(R.id.registerButton)

        //check authentication
        if(fAuth.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        mRegisterBtn.setOnClickListener {

            val email: String = mEmailRegister.text.toString().trim()
            val password: String = mPasswordRegister.text.toString().trim()

            val firstName: String = mPersonNameRegister.text.toString().toLowerCase().trim()
            val phoneNumber: Int = mPhoneNumberRegister.text.toString().toInt()

            if (TextUtils.isEmpty(email)) {
                mEmailRegister.error = "Email is required !"
            }

            if (TextUtils.isEmpty(password)) {
                mPasswordRegister.error = "Password is required !"
            }

            if (password.length < 8) {
                mPasswordRegister.error = "Password must be at 8 chars or longer !"
            }

            if (TextUtils.isEmpty(firstName)) {
                mPersonNameRegister.error = "Name is required !"
            }

            if (TextUtils.isEmpty(phoneNumber.toString())) {
                mPhoneNumberRegister.error = "Phone number is required !"
            }

            //register user in Firebase
            fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener{
                    if (it.isSuccessful) {
                        Toast.makeText(this, "User Created.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))

                        //save new user to fireStore
                        val user = User(firstName, null, email, password, null, null,
                            phoneNumber,null, null, null, null, null,
                            null)
                        saveUser(user)

                    } else {
                        Toast.makeText(this, "Error ! " + it.exception, Toast.LENGTH_SHORT).show()
                    }
                })



        }

        mLoginBtnActivity.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }

    private fun saveUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        try {
            userCollectionRef.add(user).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@RegisterActivity, "Successfully save", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}