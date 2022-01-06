package com.walkmydog.data

import java.io.Serializable
import java.util.*

data class User(
    var firstName: String,
    var lastName: String?,
    var email: String,
    var password: String,
    var gender: Gender?,
    var age: Int?,
    var phoneNumber: Int,
    var date: Date?,
    var city: String?,
    var street: String?,
    var houseNumber: String?,
    var flatNumber: String?,
    var cartNumber: Long?
        ): Serializable