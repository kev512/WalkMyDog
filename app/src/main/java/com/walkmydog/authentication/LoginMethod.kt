package com.walkmydog.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.facebook.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.facebook.CallbackManager
import com.facebook.AccessToken
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.walkmydog.MainActivity
import com.walkmydog.R
import kotlinx.android.synthetic.main.activity_login_method.*
import java.util.*


class LoginMethod : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mSignin: Button
    private lateinit var mGoogle: Button
    private lateinit var mRegister: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager



    private val TAG = "LoginMethod"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_method)


        FacebookSdk.fullyInitialize(); AppEventsLogger.activateApp(application);


        mSignin = findViewById(R.id.LoginButton)
        mGoogle = findViewById(R.id.googleSignIn)
        mRegister = findViewById(R.id.textViewCreateAccount)
        mAuth = Firebase.auth





//        buttonFacebookLogin.setOnClickListener {
//            callbackManager = CallbackManager.Factory.create()
//            LoginManager.getInstance()
//                .logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
//            LoginManager.getInstance().registerCallback(callbackManager,
//                object : FacebookCallback<LoginResult> {
//                    override fun onCancel() {
//                        Log.d(TAG, "facebook:onCancel")
//                    }
//
//                    override fun onError(error: FacebookException) {
//                        Log.d(TAG, "facebook:onError", error)
//                    }
//
//                    override fun onSuccess(result: LoginResult) {
//                        Log.d(TAG, "facebook:onSuccess:$result")
//                        handleFacebookAccessToken(result.accessToken)
//                    }
//                })
//        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id2))
            .requestEmail()
            .build()


        if(mAuth.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        mSignin.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        mRegister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))

        }




        googleSignInClient = GoogleSignIn.getClient(this,gso)



        googleSignIn.setOnClickListener {
            signIn()
        }
    }


    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e)
                }
            }
            else{
                Log.w(TAG, exception.toString())


            }

        }
        else
            callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication google failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }
//     private fun handleFacebookAccessToken(token: AccessToken) {
//        Log.d(TAG, "handleFacebookAccessToken:$token")
//
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = mAuth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication facebook failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//    }
//    private fun updateUI(user: FirebaseUser?) {
//        if(user != null) {
//            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, MainActivity::class.java))
//        } else {
//            Toast.makeText(this, "Please log in to continue", Toast.LENGTH_SHORT).show()
//        }
//    }


}
