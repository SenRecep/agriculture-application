package com.example.agricultureapplication.models.webapi.dto

import com.google.gson.annotations.SerializedName

data class AddressUpdateDto (
    @SerializedName("latitude") val Latitude: Float?=null,
    @SerializedName("longitude") val Longitude: Float?=null,
    @SerializedName("address") val Address: String?=null,
)