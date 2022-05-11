package com.example.agricultureapplication.models.user

import com.google.gson.annotations.SerializedName

data class JwtToken(@SerializedName("access_token") var AccessToken: String)