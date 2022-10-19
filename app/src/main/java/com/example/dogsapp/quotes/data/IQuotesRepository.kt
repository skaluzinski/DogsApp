package com.example.dogsapp.quotes.data

import com.example.dogsapp.quotes.data.local.SavedQuotes
import com.example.dogsapp.quotes.data.remote.QuoteResponse

interface IQuotesRepository {
    suspend fun fetchAllQuotes(): List<QuoteResponse>

    suspend fun fetchSingleQuote(): QuoteResponse

    suspend fun fetchSavedQuotes(): List<SavedQuotes>
    fun getIdOfQuote(quoteResponse: QuoteResponse): Int

    suspend fun saveQuote(quoteResponse: QuoteResponse)

    suspend fun checkIfQuoteIsSaved(quoteResponse: QuoteResponse): Boolean

    suspend fun deleteQuote(quoteResponse: QuoteResponse)
}