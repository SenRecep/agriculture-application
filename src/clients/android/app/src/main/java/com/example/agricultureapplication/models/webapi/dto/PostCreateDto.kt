package com.example.agricultureapplication.models.webapi.dto

import com.google.gson.annotations.SerializedName

data class PostCreateDto(
    @SerializedName("name") val Name: String,
    @SerializedName("content") val Content: String,
    @SerializedName("fertilizer") val Fertilizer: String,
    @SerializedName("irrigation") val Irrigation: String,
    @SerializedName("planting") val Planting: String,
    @SerializedName("harvest") val Harvest: String,
)