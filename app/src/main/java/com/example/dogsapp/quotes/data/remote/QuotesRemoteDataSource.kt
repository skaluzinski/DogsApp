package com.example.dogsapp.quotes.data.remote

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.dogs.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRemoteDataSource @Inject constructor(
    private val quotesApi: QuotesApi,
    @DefaultDispatcher private val defaultDispatcher:  CoroutineDispatcher
) : IQuotesRemoteDataSource {
    override suspend fun getRandomQuote(): QuoteResponse = withContext(defaultDispatcher){
        quotesApi.getRandomQuote()
    }

    override suspend fun getAllQuotes(): List<QuoteResponse> = withContext(defaultDispatcher){
        quotesApi.getAllQuotes()
    }

}