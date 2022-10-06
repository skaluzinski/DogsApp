package com.example.dogsapp.quotes.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface QuotesApi {

    @GET("randomQuote")
    suspend fun getRandomQuote(): QuoteResponse

    @GET("quotes")
    suspend fun getAllQuotes(): List<QuoteResponse>
}