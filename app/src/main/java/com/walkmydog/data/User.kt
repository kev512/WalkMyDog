package com.walkmydog.data

import java.io.Serializable
import java.util.*

data class User(
    var firstName: String = "",
    var lastName: String? = "",
    var email: String = "",
    var gender: Gender? = Gender.ANOTHER,
    var phoneNumber: Int = -1,
    var city: String? = "",
    var street: String? = "",
    var houseNumber: Int? = -1,
    var flatNumber: Int? = -1,
    var cardNumber: Long? = -1
        ): Serializable