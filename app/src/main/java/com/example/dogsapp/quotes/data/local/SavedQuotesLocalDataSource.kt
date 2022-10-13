package com.example.dogsapp.quotes.data.local

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject



class SavedQuotesLocalDataSource @Inject constructor(
    private val savedQuotesDatabase: SavedQuotesDao,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ISavedQuotesLocalDataSource {
    override suspend fun saveQuote(quoteResponse: QuoteResponse) = withContext(defaultDispatcher) {
        val item = SavedQuotes(author = quoteResponse.author, quote = quoteResponse.quote)
        savedQuotesDatabase.saveQuote(item)
    }

    override suspend fun deleteQuote(quoteResponse: QuoteResponse) = withContext(defaultDispatcher) {
        val item = SavedQuotes(author = quoteResponse.author, quote = quoteResponse.quote)
        savedQuotesDatabase.deleteQuote(item)
    }

    override suspend fun getSavedQuotes(): Flow<List<SavedQuotes>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSavedQuotes() {
        TODO("This function should not be called in this feature")
    }

//    override suspend fun getSavedQuotes() = withContext(defaultDispatcher){
//        savedQuotesDatabase.getSavedQuotes()
//    }

    
}