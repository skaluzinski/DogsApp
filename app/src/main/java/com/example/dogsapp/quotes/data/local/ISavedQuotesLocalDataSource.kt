package com.example.dogsapp.quotes.data.local

import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface ISavedQuotesLocalDataSource {
    suspend fun saveQuote(quoteResponse: QuoteResponse)

    suspend fun deleteQuote(quoteResponse: QuoteResponse)

    suspend fun getSavedQuotes(): Flow<List<SavedQuotes>>

    suspend fun deleteSavedQuotes()
}