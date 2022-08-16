package com.example.dogsapp.network.photos

import com.squareup.moshi.Json

data class DogsPhoto(
    @Json(name = "message")
    val photos: List<String>,

    @Json(name = "status")
    val status: String
)