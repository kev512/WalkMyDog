package com.walkmydog.data

import java.time.format.DateTimeFormatter

data class Announcement(
    val userId: String? = "",
    val dogId: String? = "",
    val user: User? = User(),
    val dog: Dog = Dog(),
    val walkMinuteDuration: Int? = -1,
    val information: String? = ""
)