package com.example.agricultureapplication.models.user

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("id") var Id: Number,
    @SerializedName("userName") var UserName: String,
    @SerializedName("firstName") var FirstName: String,
    @SerializedName("lastName") var LastName: String,
    @SerializedName("email") var Email: String,
    @SerializedName("password") var Password: String,
    @SerializedName("isAdmin") var IsAdmin: Boolean
)