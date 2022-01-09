package com.walkmydog.ui.profile.changepersonaldata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.walkmydog.R
import com.walkmydog.data.Gender
import com.walkmydog.data.User
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class ChangePersonalDataActivity : AppCompatActivity() {

    private lateinit var mPayCard: EditText
    private lateinit var mCity: EditText
    private lateinit var mStreet: EditText
    private lateinit var mHouse: EditText
    private lateinit var mFlat: EditText
    private lateinit var mFirstName: EditText
    private lateinit var mLastName: EditText
    private lateinit var mPhoneNumber: EditText
    private lateinit var mGender: EditText
    private lateinit var mSaveEditedData: Button

    private lateinit var mShowMeUsers: TextView
    private lateinit var mCurrentUser: User


    private val userCollectionRef = FirebaseFirestore.getInstance().collection("Users")
    private val fAuth = FirebaseAuth.getInstance().currentUser


    private var mUsers = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_personal_data)

        mPayCard = findViewById(R.id.editCreditCard)
        mCity = findViewById(R.id.editCity)
        mStreet = findViewById(R.id.editStreet)
        mHouse = findViewById(R.id.editHomeNumber)
        mFlat = findViewById(R.id.editFlatNumber)
        mFirstName = findViewById(R.id.editPersonFirstName)
        mLastName = findViewById(R.id.editPersonLastName)
        mPhoneNumber = findViewById(R.id.editPhoneNumber)
        mGender = findViewById(R.id.editGender)


        mShowMeUsers = findViewById(R.id.showMeUser)




        mSaveEditedData = findViewById(R.id.saveEditedPersonalDataBtn)
        mSaveEditedData.setOnClickListener {

            getOldUserData()



            val payCard: Long = mPayCard.text.toString().toLong()
            val city: String = mCity.text.toString()
            val street: String = mStreet.text.toString()
            val house: Int = mHouse.text.toString().toInt()
            val flat: Int = mFlat.text.toString().toInt()

            val firstName: String = mFirstName.text.toString()
            val lastName: String = mLastName.text.toString()
            val phoneNumber: Int = mPhoneNumber.text.toString().toInt()

            val gender = if (mGender.text.toString().toLowerCase() == "mężczyzna" ||
                mGender.text.toString().toLowerCase() == "męzczyzna" ||
                mGender.text.toString().toLowerCase() == "meżczyzna" ||
                mGender.text.toString().toLowerCase() == "men") {

                Gender.MEN
            } else if (mGender.text.toString().toLowerCase() == "kobieta" ||
                mGender.text.toString().toLowerCase() == "women") {

                Gender.WOMEN
            } else {

                Gender.ANOTHER
            }

            if (TextUtils.isEmpty(payCard.toString())) {
                mPayCard.error = "Pay card is required !"
            }

            if (TextUtils.isEmpty(city)) {
                mCity.error = "City is required !"
            }

            if (TextUtils.isEmpty(street)) {
                mStreet.error = "Street is required !"
            }

            if (TextUtils.isEmpty(house.toString())) {
                mHouse.error = "Home is required !"
            }

            if (TextUtils.isEmpty(flat.toString())) {
                mFlat.error = "Flat is required !"
            }

            if (TextUtils.isEmpty(firstName)) {
                mFirstName.error = "First name is required !"
            }

            if (TextUtils.isEmpty(lastName)) {
                mLastName.error = "Last name is required !"
            }

            if (TextUtils.isEmpty(phoneNumber.toString())) {
                mPhoneNumber.error = "Phone number is required !"
            }

            if (TextUtils.isEmpty(gender.toString())) {
                mGender.error = "Gender is required !"
            }

            if (payCard.toString().length != 16) {
                mPayCard.error = "Pay card must have 16 chars !"
            }

            if (phoneNumber.toString().length != 9) {
                mPayCard.error = "Phone number must have 9 chars !"
            }

            val newUser = User(
                firstName,
                lastName,
                fAuth!!.email.toString(),
                gender,
                phoneNumber,
                city,
                street,
                house,
                flat,
                payCard
            )
            changeDataUser(newUser)


        }

    }

    private fun getOldUserData(): Job = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = userCollectionRef.get().await()
            for (document in querySnapshot.documents){
                val user = document.toObject<User>()
                val userToSaveInList: User = user!!
                mUsers.add(userToSaveInList)
            }

        } catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@ChangePersonalDataActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeDataUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        try {
            userCollectionRef.document(fAuth!!.uid).set(user)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@ChangePersonalDataActivity, "Successfully save", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this@ChangePersonalDataActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }


}