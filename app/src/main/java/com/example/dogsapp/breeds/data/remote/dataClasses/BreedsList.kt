package com.example.dogsapp.breeds.data.remote.dataClasses

import com.squareup.moshi.Json

data class BreedsList(
    @Json(name = "message")
    val breeds: Map<String,List<String>>,

    @Json(name = "status")
    val status: String
)