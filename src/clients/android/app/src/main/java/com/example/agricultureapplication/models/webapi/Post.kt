package com.example.agricultureapplication.models.webapi

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Post(
    @SerializedName("id") val Id: Number,
    @SerializedName("createdTime") val CreatedTime: Date,
    @SerializedName("updatedTime") val UpdatedTime: Date,
    @SerializedName("isDeleted") val IsDeleted: Boolean,
    @SerializedName("name") val Name: String,
    @SerializedName("content") val Content: String,
    @SerializedName("userId") val UserId: Number,
    @SerializedName("fertilizer") val Fertilizer: String,
    @SerializedName("irrigation") val Irrigation: String,
    @SerializedName("planting") val Planting: String,
    @SerializedName("harvest") val Harvest: String,
) : Parcelable {
    companion object {
        fun createEmptyPost(): Post {
            return Post(
                Id = 0,
                CreatedTime = Date(),
                UpdatedTime = Date(),
                IsDeleted = false,
                UserId = 0,
                Content = "",
                Name = "",
                Fertilizer = "",
                Irrigation = "",
                Planting = "",
                Harvest = "",
            )
        }
    }
}

