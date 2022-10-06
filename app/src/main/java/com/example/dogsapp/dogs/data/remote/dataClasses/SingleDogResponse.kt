package com.example.dogsapp.dogs.data.remote.dataClasses

import com.squareup.moshi.Json

data class SingleDogResponse(
    @Json(name = "message")
    val link: String,

    @Json(name = "status")
    val status: String
)