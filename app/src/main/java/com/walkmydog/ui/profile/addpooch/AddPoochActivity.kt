package com.walkmydog.ui.profile.addpooch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.walkmydog.R
import com.walkmydog.data.Dog
import com.walkmydog.data.User
import com.walkmydog.ui.profile.ProfileFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AddPoochActivity : AppCompatActivity() {

    private lateinit var mPoochName: EditText
    private lateinit var mPoochAge: EditText
    private lateinit var mPoochRace: EditText
    private lateinit var mPoochWeight: EditText
    private lateinit var mPoochAdditionalInfo: EditText
    private lateinit var mPoochAddButton: Button
    private lateinit var mPoochQuestion1: Switch
    private lateinit var mPoochQuestion2: Switch
    private lateinit var mPoochQuestion3: Switch
    private lateinit var mPoochQuestion4: Switch
    private lateinit var mPoochQuestion5: Switch
    private lateinit var mPoochQuestion6: Switch
    private lateinit var mPoochQuestion7: Switch
    private lateinit var mPoochQuestion8: Switch
    private lateinit var mPoochQuestion9: Switch
    private lateinit var mPoochQuestion10: Switch
    private lateinit var mPoochQuestion11: Switch
    private lateinit var mPoochQuestion12: Switch

    private val dogsCollectionRef = FirebaseFirestore.getInstance().collection("Dogs")
    private val userId: String = FirebaseAuth.getInstance().uid!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pooch)


        mPoochName = findViewById(R.id.poochName)
        mPoochAge = findViewById(R.id.poochAge)
        mPoochRace = findViewById(R.id.poochRace)
        mPoochWeight = findViewById(R.id.poochWeight)
        mPoochAdditionalInfo = findViewById(R.id.poochAdditionalInfo)
        mPoochAddButton = findViewById(R.id.addPoochButton)
        mPoochQuestion1 = findViewById(R.id.switch1)
        mPoochQuestion2 = findViewById(R.id.switch2)
        mPoochQuestion3 = findViewById(R.id.switch3)
        mPoochQuestion4 = findViewById(R.id.switch4)
        mPoochQuestion5 = findViewById(R.id.switch5)
        mPoochQuestion6 = findViewById(R.id.switch6)
        mPoochQuestion7 = findViewById(R.id.switch7)
        mPoochQuestion8 = findViewById(R.id.switch8)
        mPoochQuestion9 = findViewById(R.id.switch9)
        mPoochQuestion10 = findViewById(R.id.switch10)
        mPoochQuestion11 = findViewById(R.id.switch11)
        mPoochQuestion12 = findViewById(R.id.switch12)



        mPoochAddButton.setOnClickListener {

            val poochName: String = mPoochName.text.toString().toLowerCase().trim()
            val poochAge: Int = mPoochAge.text.toString().toInt()
            val poochRace : String = mPoochRace.text.toString().toLowerCase().trim()
            val poochWeight: Float = mPoochWeight.text.toString().toFloat()

            if(TextUtils.isEmpty(poochName)){
                mPoochName.error = "Pooch name is required !"
            }

            if(TextUtils.isEmpty(poochAge.toString())){
                mPoochAge.error = "Pooch age is required !"
            }

            if(TextUtils.isEmpty(poochRace)){
                mPoochRace.error = "Pooch race is required !"
            }

            if(TextUtils.isEmpty(poochWeight.toString())){
                mPoochWeight.error = "Pooch weight is required !"
            }


            val poochAdditionalInfo: String = mPoochAdditionalInfo.text.toString()
            val poochQuestion1: Boolean = mPoochQuestion1.text.toString().toBoolean()
            val poochQuestion2: Boolean = mPoochQuestion2.text.toString().toBoolean()
            val poochQuestion3: Boolean = mPoochQuestion3.text.toString().toBoolean()
            val poochQuestion4: Boolean = mPoochQuestion4.text.toString().toBoolean()
            val poochQuestion5: Boolean = mPoochQuestion5.text.toString().toBoolean()
            val poochQuestion6: Boolean = mPoochQuestion6.text.toString().toBoolean()
            val poochQuestion7: Boolean = mPoochQuestion7.text.toString().toBoolean()
            val poochQuestion8: Boolean = mPoochQuestion8.text.toString().toBoolean()
            val poochQuestion9: Boolean = mPoochQuestion9.text.toString().toBoolean()
            val poochQuestion10: Boolean = mPoochQuestion10.text.toString().toBoolean()
            val poochQuestion11: Boolean = mPoochQuestion11.text.toString().toBoolean()
            val poochQuestion12: Boolean = mPoochQuestion12.text.toString().toBoolean()

            var questions = ArrayList<Boolean>()

            questions.add(poochQuestion1)
            questions.add(poochQuestion2)
            questions.add(poochQuestion3)
            questions.add(poochQuestion4)
            questions.add(poochQuestion5)
            questions.add(poochQuestion6)
            questions.add(poochQuestion7)
            questions.add(poochQuestion8)
            questions.add(poochQuestion9)
            questions.add(poochQuestion10)
            questions.add(poochQuestion11)
            questions.add(poochQuestion12)


            //save new dog to fireStore
            var dog = Dog(
                FirebaseAuth.getInstance().uid.toString(),
                poochName,
                poochRace,
                poochAge,
                poochWeight,
                questions,
                poochAdditionalInfo
            )
            saveDog(dog)

        }

    }


    private fun saveDog(dog: Dog) = CoroutineScope(Dispatchers.IO).launch {
        try {
            //save with parameters "userId + dogName"
            dogsCollectionRef.document(userId + ";" + dog.name).set(dog).await()

            withContext(Dispatchers.Main) {
                Toast.makeText(this@AddPoochActivity, "Successfully save", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@AddPoochActivity, ProfileFragment::class.java))
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@AddPoochActivity, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }
}