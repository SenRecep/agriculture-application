package com.example.agricultureapplication.models.webapi.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressListDto(
    val Distance: Float,
    val DistanceText: String,
    val Address: String,
):Parcelable