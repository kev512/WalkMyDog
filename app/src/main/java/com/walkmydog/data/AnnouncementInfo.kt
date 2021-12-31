package com.walkmydog.data

import java.time.format.DateTimeFormatter

data class AnnouncementInfo (
    val date: DateTimeFormatter,
    val userId: String,
    val dogId: String,
    val userInfo: UserInfo,
    val dogInfo: DogInfo,
    val walkMinuteDuration: Int,
    val information: String
)