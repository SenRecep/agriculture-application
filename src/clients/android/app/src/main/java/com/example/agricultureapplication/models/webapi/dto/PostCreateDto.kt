package com.example.agricultureapplication.models.webapi.dto

import com.google.gson.annotations.SerializedName

data class PostCreateDto(
    @SerializedName("title") val Title: String,
    @SerializedName("content") val Content: String,
    @SerializedName("address") var Address: AddressCreateDto,
)