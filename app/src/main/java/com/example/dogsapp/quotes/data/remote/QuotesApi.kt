package com.example.dogsapp.quotes.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface QuotesApi {

    @GET("")
    suspend fun getRandomQuote(): QuoteResponse

    @GET("&limit={amount}")
    suspend fun getRandomQuotes(
        amount: Int = 1
    ): List<QuoteResponse>

    @GET("{category}&limit={amount}")
    suspend fun getQuotesByCategory(
        @Path("category") category: String,
        @Path("amount") amount: Int? = 1
    ): List<QuoteResponse>

}