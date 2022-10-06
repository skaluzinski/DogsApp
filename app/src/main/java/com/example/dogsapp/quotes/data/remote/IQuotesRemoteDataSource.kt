package com.example.dogsapp.quotes.data.remote

interface IQuotesRemoteDataSource {
    suspend fun getRandomQuote(): QuoteResponse
    suspend fun getAllQuotes(): List<QuoteResponse>
}