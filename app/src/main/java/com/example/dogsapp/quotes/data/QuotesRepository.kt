package com.example.dogsapp.quotes.data

import com.example.dogsapp.quotes.data.local.SavedQuotes
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.quotes.data.remote.IQuotesRemoteDataSource
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val savedQuotesLocalDataSource: SavedQuotesDao,
    private val quotesRemoteDataSource: IQuotesRemoteDataSource
) {
    suspend fun fetchAllQuotes() = quotesRemoteDataSource.getAllQuotes()

    suspend fun fetchSingleQuote() = quotesRemoteDataSource.getRandomQuote()

    suspend fun fetchSavedQuotes() = savedQuotesLocalDataSource.getSavedQuotes()

    suspend fun saveQuote(quoteResponse: QuoteResponse) {
        savedQuotesLocalDataSource.saveQuote(
            SavedQuotes(
                author = quoteResponse.author,
                quote = quoteResponse.quote
            )
        )
    }

    suspend fun checkIfQuoteIsSaved(quoteResponse: QuoteResponse): Boolean {
        return savedQuotesLocalDataSource.checkIfSaved(quoteResponse.author, quoteResponse.quote)
    }

}