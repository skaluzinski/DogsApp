package com.example.dogsapp.quotes.data.remote

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.dogs.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRemoteDataSource @Inject constructor(
    private val quotesApi: QuotesApi,
    private val defaultDispatcher: CoroutineDispatcher
) : IQuotesRemoteDataSource {
    override suspend fun getRandomQuote(): QuoteResponse = withContext(defaultDispatcher){
        quotesApi.getRandomQuote()
    }

    override suspend fun getRandomQuotes(amount: Int?): List<QuoteResponse> = withContext(defaultDispatcher){
        TODO("Not yet implemented")
    }

    override suspend fun getRandomQuoteByCategory(category: String): QuoteResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomQuotesByCategory(
        category: String,
        amount: Int
    ): List<QuoteResponse> {
        TODO("Not yet implemented")
    }

}