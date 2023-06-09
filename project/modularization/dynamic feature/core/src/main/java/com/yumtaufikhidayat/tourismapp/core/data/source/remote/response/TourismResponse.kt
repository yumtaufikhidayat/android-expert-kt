package com.yumtaufikhidayat.tourismapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TourismResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("like")
    val like: Int,
    @SerializedName("image")
    val image: String
)
