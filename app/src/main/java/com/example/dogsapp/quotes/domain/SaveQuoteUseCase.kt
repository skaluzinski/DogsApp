package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.QuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject



class SaveQuoteUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : ISaveQuoteUseCase {
    override suspend fun execute(quoteResponse: QuoteResponse) = withContext(defaultDispatcher){
        quotesRepository.saveQuote(quoteResponse)
    }
}