package com.walkmydog.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.walkmydog.MainActivity
import com.walkmydog.R
import org.jetbrains.annotations.NotNull

class RegisterActivity : AppCompatActivity() {

   private lateinit var mPersonNameRegister: EditText
   private lateinit var mEmailRegister: EditText
   private lateinit var mPasswordRegister: EditText
   private lateinit var mPhoneNumberRegister: EditText
   private lateinit var mLoginBtnActivity: TextView
   private lateinit var mRegisterBtn: Button
   private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPersonNameRegister = findViewById(R.id.editTextPersonNameRegister)
        mEmailRegister = findViewById(R.id.editTextEmailRegister)
        mPasswordRegister = findViewById(R.id.editTextPasswordRegister)
        mPhoneNumberRegister = findViewById(R.id.editTextPhoneRegister)
        mLoginBtnActivity = findViewById(R.id.loginTextViewInRegisterActivity)
        mRegisterBtn = findViewById(R.id.registerButton)

        fAuth = FirebaseAuth.getInstance()

        //check auth
        if(fAuth.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        mRegisterBtn.setOnClickListener(View.OnClickListener {
            fun onClick() {
                val email: String = mEmailRegister.text.toString().trim()
                val password: String = mPasswordRegister.text.toString().trim()

                if(TextUtils.isEmpty(email)){
                    mEmailRegister.setError("Email is required !")
                }

                if(TextUtils.isEmpty(password)){
                    mPasswordRegister.setError("Password is required !")
                }

                if(password.length < 8){
                    mPasswordRegister.setError("Password must be at 8 chars or longer !")
                }


                //register user in Firebase
                fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(OnCompleteListener {
                        fun onComplete(task: Task<AuthResult>){
                            if(task.isSuccessful){
                               Toast.makeText(this, "User Created.", Toast.LENGTH_SHORT).show()
                               startActivity(Intent(applicationContext, MainActivity::class.java))
                            }else {
                                Toast.makeText(this, "Error ! " + task.exception, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
            }

        })
    }
}