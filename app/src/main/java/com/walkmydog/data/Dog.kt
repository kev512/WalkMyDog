package com.walkmydog.data

data class Dog (
    val userId: String,
    val name: String,
    val race: String,
    val age: Int,
    val weight: Float,
    val petBehaviorQuestions: ArrayList<Boolean>,
    val AdditionalInfo: String?
)