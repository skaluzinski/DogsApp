package com.example.dogsapp.breeds

import com.squareup.moshi.Json

data class BreedList(
    @Json(name = "message")
    val breeds: Map<String, List<String>>,

    @Json(name = "status")
    val status: String
)