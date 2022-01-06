package com.walkmydog.data

import java.time.format.DateTimeFormatter

data class Announcement (
    val userId: String,
    val dogId: String,
    val date: DateTimeFormatter,
    val user: User,
    val dogInfo: Dog,
    val walkMinuteDuration: Int,
    val information: String
)