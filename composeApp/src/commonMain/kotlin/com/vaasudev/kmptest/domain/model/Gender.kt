package com.vaasudev.kmptest.domain.model

data class Gender(
    val id: Int,
    val title: String,
)

val genders = listOf(
    Gender(
        id = 1,
        title = "Woman"
    ),
    Gender(
        id = 2,
        title = "Man"
    ),
    Gender(
        id = 3,
        title = "Transgender person"
    ),
    Gender(
        id = 4,
        title = "None of the above"
    ),
    Gender(
        id = 5,
        title = "Remove my gender information"
    ),
)