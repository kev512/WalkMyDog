package com.walkmydog.ui.addannouncement.addnewannouncement


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.walkmydog.R
import com.walkmydog.data.Announcement
import com.walkmydog.data.Dog
import com.walkmydog.data.QuestionsData
import com.walkmydog.data.User
import com.walkmydog.ui.addannouncement.AddAnnouncementFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class NewAnnouncementActivity : AppCompatActivity() {

    private lateinit var mDogName: EditText
    private lateinit var mWalkInMin: EditText
    private lateinit var mAdditionalInfo: EditText
    private lateinit var mSaveAnnouncement: Button
    private lateinit var mShowMeThings: TextView
    private lateinit var mShowMeThings2: TextView


    private val db = Firebase.firestore

    private val userAuth = FirebaseAuth.getInstance()
    private val announcementCollectionRef = Firebase.firestore.collection("Announcements")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_new_announcement)

        mDogName = findViewById(R.id.dogName)
        mWalkInMin = findViewById(R.id.walkMinTime)
        mAdditionalInfo = findViewById(R.id.additionalInformation)
        mSaveAnnouncement = findViewById(R.id.saveNewAnnouncementBtn)

//        mShowMeThings = findViewById(R.id.boxToThings)
//        mShowMeThings2 = findViewById(R.id.boxToThings2)



        var user = User()

        mSaveAnnouncement.setOnClickListener {

            val dogName: String = mDogName.text.toString()
            val walkInMin: Int = mWalkInMin.text.toString().toInt()
            val additionalInfo: String = mAdditionalInfo.text.toString()


            if (TextUtils.isEmpty(dogName)) {
                mDogName.error = "Dog name is required !"
            }

            if (TextUtils.isEmpty(walkInMin.toString())) {
                mWalkInMin.error = "Field is required !"
            }

            db.collection("Users").document(userAuth.currentUser?.uid.toString()).get()
                .addOnCompleteListener {
                    val user = it.result.toObject(User::class.java)
                    db.collection("Dogs").document(userAuth.currentUser?.uid.toString() + ";" + dogName )
                        .get()
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val dog = it.result.toObject(Dog::class.java)

                                val userId = userAuth.currentUser?.uid
                                val dogId = userAuth.currentUser?.uid + ";" + dogName


                                val newAnnouncement = Announcement(
                                    userId,
                                    dogId,
                                    user!!,
                                    dog!!,
                                    walkInMin,
                                    additionalInfo
                                )
                                saveAnnouncement(dog, newAnnouncement)
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(applicationContext, "Dog with this name doesn't exists", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "User with this name doesn't exists", Toast.LENGTH_SHORT).show()
                }

        }

    }

    private fun saveAnnouncement(dog: Dog, announcement: Announcement) = CoroutineScope(Dispatchers.IO).launch {
        try {
            //save with parameters "userId + dogName"
            announcementCollectionRef.document(userAuth.currentUser?.uid + ";" + dog.name).set(announcement).await()

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Successfully save", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, AddAnnouncementFragment::class.java))
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }


}

