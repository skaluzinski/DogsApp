package com.example.dogsapp.dogs.data.remote.dataClasses

import com.squareup.moshi.Json

data class OneRacePhotos(
    @Json(name = "message")
    val photos: List<String>,

    @Json(name = "status")
    val status: String
)