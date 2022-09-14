package com.example.dogsapp.quotes.data.remote

interface IQuotesRemoteDataSource {
    suspend fun getRandomQuote(): QuoteResponse
    suspend fun getRandomQuotes(amount: Int?): List<QuoteResponse>
    suspend fun getRandomQuoteByCategory(category: String): QuoteResponse
    suspend fun getRandomQuotesByCategory(category: String, amount: Int): List<QuoteResponse>
}