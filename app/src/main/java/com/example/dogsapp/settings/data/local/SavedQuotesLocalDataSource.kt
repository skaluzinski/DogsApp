package com.example.dogsapp.settings.data.local

import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.quotes.data.local.ISavedQuotesLocalDataSource
import com.example.dogsapp.quotes.data.local.SavedQuotes
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedQuotesLocalDataSource @Inject constructor(
    private val savedQuotesDatabase: SavedQuotesDao,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ISavedQuotesLocalDataSource {
    override suspend fun saveQuote(quoteResponse: QuoteResponse) {
        TODO("This function should not be called in this feature")
    }

    override suspend fun deleteQuote(quoteResponse: QuoteResponse) {
        TODO("This function should not be called in this feature")
    }

    override suspend fun getSavedQuotes(): Flow<List<SavedQuotes>> {
        TODO("This function should not be called in this feature")
    }

    override suspend fun deleteSavedQuotes() {
        savedQuotesDatabase.deleteSavedQuotes()
    }
}