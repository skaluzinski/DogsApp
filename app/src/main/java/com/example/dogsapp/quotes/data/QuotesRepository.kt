package com.example.dogsapp.quotes.data

import android.util.Log
import com.example.dogsapp.dogs.di.DefaultDispatcher
import com.example.dogsapp.quotes.data.local.SavedQuotes
import com.example.dogsapp.quotes.data.local.SavedQuotesDao
import com.example.dogsapp.quotes.data.remote.IQuotesRemoteDataSource
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val savedQuotesLocalDataSource: SavedQuotesDao,
    private val quotesRemoteDataSource: IQuotesRemoteDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun fetchAllQuotes() = quotesRemoteDataSource.getAllQuotes()

    suspend fun fetchSingleQuote() = quotesRemoteDataSource.getRandomQuote()

    suspend fun fetchSavedQuotes() = savedQuotesLocalDataSource.getSavedQuotes()

    fun getIdOfQuote(quoteResponse: QuoteResponse) =
        savedQuotesLocalDataSource.getIdOfQuote(
            authorToFind = quoteResponse.author,
            quoteToFind = quoteResponse.quote
        )

    suspend fun saveQuote(quoteResponse: QuoteResponse) {
        savedQuotesLocalDataSource.saveQuote(
            SavedQuotes(
                author = quoteResponse.author,
                quote = quoteResponse.quote
            )
        )
    }

    suspend fun checkIfQuoteIsSaved(quoteResponse: QuoteResponse): Boolean =
        withContext(defaultDispatcher) {
            savedQuotesLocalDataSource.checkIfSaved(quoteResponse.author, quoteResponse.quote)
        }

    suspend fun deleteQuote(quoteResponse: QuoteResponse) = withContext(defaultDispatcher) {
        Log.d("ID",getIdOfQuote(quoteResponse).toString(),)
        savedQuotesLocalDataSource.deleteQuote(
            SavedQuotes(
                id = getIdOfQuote(quoteResponse),
                author = quoteResponse.author,
                quote = quoteResponse.quote
            )
        )
    }
}