package com.example.agricultureapplication.models.user

import com.google.gson.annotations.SerializedName

data class UserSignIn(
    @SerializedName("email") var Email: String,
    @SerializedName("password") var Password: String
)