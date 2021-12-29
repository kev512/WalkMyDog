package com.walkmydog.data

import java.io.Serializable
import java.util.*

data class UserInfo (
    val firstName: String,
    val lastName: String,
    val email: String,  //ID
    val password: String,
    val gender: Boolean,
    val age: Int,
    val phoneNumber: Int,
    val date: Date,
    val city: String,
    val street: String,
    val houseNumber: String,
    val flatNumber: String,
    val cartNumber: Long
        ): Serializable