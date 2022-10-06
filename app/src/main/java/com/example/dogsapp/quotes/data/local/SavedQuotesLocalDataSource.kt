package com.example.dogsapp.quotes.data.local

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavedQuotesLocalDataSource @Inject constructor(
    private val savedQuotesDatabase: SavedQuotesDao,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun saveQuote(quoteResponse: QuoteResponse) = withContext(defaultDispatcher) {
        val item = SavedQuotes(author = quoteResponse.author, quote = quoteResponse.quote)
        savedQuotesDatabase.saveQuote(item)
    }

    suspend fun deleteQuote(quoteResponse: QuoteResponse) = withContext(defaultDispatcher) {
        val item = SavedQuotes(author = quoteResponse.author, quote = quoteResponse.quote)
        savedQuotesDatabase.deleteQuote(item)
    }

    
}