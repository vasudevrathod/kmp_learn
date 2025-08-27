package com.vaasudev.kmptest.domain.model

data class PersonalInfo(
    val id: Int,
    val title: String,
    val info: String
)

val personalInfo = listOf(
    PersonalInfo(
        id = 1,
        title = "Name",
        info = "John Doe"
    ),
    PersonalInfo(
        id = 2,
        title = "Gender",
        info = "Male"
    ),
    PersonalInfo(
        id = 3,
        title = "Mobile",
        info = "+911234567890"
    ),
    PersonalInfo(
        id = 4,
        title = "Email",
        info = ""
    )
)
