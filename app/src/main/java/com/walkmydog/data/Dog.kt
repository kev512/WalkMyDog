package com.walkmydog.data

data class Dog (
    val userId: String = "",
    val name: String = "",
    val race: String = "",
    val age: Int = -1,
    val weight: Float = (-1).toFloat(),
    val questions: ArrayList<Boolean> = ArrayList(),
    val AdditionalInfo: String? = ""
)