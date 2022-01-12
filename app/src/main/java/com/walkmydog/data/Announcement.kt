package com.walkmydog.data

import java.time.format.DateTimeFormatter

data class Announcement (
    val userId: String?,
    val dogId: String?,
    val user: User?,
    val dog: Dog?,
    val walkMinuteDuration: Int?,
    val information: String?
)