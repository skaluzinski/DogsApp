package com.example.dogsapp.quotes.data.remote

import com.example.dogsapp.quotes.data.local.QuoteState
import com.squareup.moshi.Json

data class QuoteResponse(
    @Json(name = "quote")
    val quote: String,

    @Json(name = "author")
    val author: String,

    var isSaved: QuoteState = QuoteState.UNKNOWN
)
