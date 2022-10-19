package com.example.dogsapp.quotes.data

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
) : IQuotesRepository {
    override suspend fun fetchAllQuotes() = quotesRemoteDataSource.getAllQuotes()

    override suspend fun fetchSingleQuote() = quotesRemoteDataSource.getRandomQuote()

    override suspend fun fetchSavedQuotes() = savedQuotesLocalDataSource.getSavedQuotes()

    override fun getIdOfQuote(quoteResponse: QuoteResponse) =
        savedQuotesLocalDataSource.getIdOfQuote(
            authorToFind = quoteResponse.author,
            quoteToFind = quoteResponse.quote
        )

    override suspend fun saveQuote(quoteResponse: QuoteResponse) {
        savedQuotesLocalDataSource.saveQuote(
            SavedQuotes(
                author = quoteResponse.author,
                quote = quoteResponse.quote
            )
        )
    }

    override suspend fun checkIfQuoteIsSaved(quoteResponse: QuoteResponse): Boolean =
        withContext(defaultDispatcher) {
            savedQuotesLocalDataSource.checkIfSaved(quoteResponse.author, quoteResponse.quote)
        }

    override suspend fun deleteQuote(quoteResponse: QuoteResponse) = withContext(defaultDispatcher) {
        savedQuotesLocalDataSource.deleteQuote(
            SavedQuotes(
                id = getIdOfQuote(quoteResponse),
                author = quoteResponse.author,
                quote = quoteResponse.quote
            )
        )
    }
}