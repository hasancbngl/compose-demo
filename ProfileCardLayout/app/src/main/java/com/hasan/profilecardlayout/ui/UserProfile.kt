package com.hasan.profilecardlayout.ui

import com.hasan.profilecardlayout.R

data class UserProfile constructor(val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = mutableListOf(
    UserProfile(name = "John Doe", status = true, R.drawable.profile_picture),
    UserProfile(name = "Anna Joans", status = false, R.drawable.profile_picture2),
    UserProfile(name = "Hasan", status = false, R.drawable.profile_picture),
    UserProfile(name = "Eli", status = true, R.drawable.profile_picture2),
)
