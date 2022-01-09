package com.walkmydog.ui.profile.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.walkmydog.R
import org.w3c.dom.Text
import java.lang.Exception

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var mNewPassword: EditText
    private lateinit var mNewPasswordToCheck: EditText
    private lateinit var mOldPassword: EditText
    private lateinit var mSaveNewPasswordBtn: Button

    private val fAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        mNewPassword = findViewById(R.id.editNewPassText)
        mOldPassword = findViewById(R.id.editOldPassText)
        mNewPasswordToCheck = findViewById(R.id.editNewPassAgainText)
        mSaveNewPasswordBtn = findViewById(R.id.saveNewPassButton)


        mSaveNewPasswordBtn.setOnClickListener {
            val newPassword: String = mNewPassword.text.toString()
            val oldPassword: String = mOldPassword.text.toString()
            val newPasswordToCheck: String = mNewPasswordToCheck.text.toString()


            if (TextUtils.isEmpty(newPassword)) {
                mNewPassword.error = "This field is required !"
            }

            if (TextUtils.isEmpty(oldPassword)) {
                mOldPassword.error = "This field is required !"
            }

            if (TextUtils.isEmpty(newPasswordToCheck)) {
                mNewPasswordToCheck.error = "This field is required !"
            }

            if(newPassword.length < 8){
                mNewPassword.error = "new password must have minimum 8 chars"
            }


            if (newPassword == newPasswordToCheck) {
                setNewPassword(oldPassword, newPassword)

            } else {
                Toast.makeText(applicationContext, "Password are different", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setNewPassword(oldPass: String, newPass: String){
        try {

            val credential: AuthCredential = EmailAuthProvider.getCredential(fAuth.currentUser!!.email.toString(), oldPass)

            fAuth.currentUser?.reauthenticate(credential)?.addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Re-Authentication success",
                        Toast.LENGTH_SHORT
                    ).show()

                    fAuth.currentUser?.updatePassword(newPass)?.addOnCompleteListener {
                        Toast.makeText(
                            applicationContext,
                            "Password changed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}


