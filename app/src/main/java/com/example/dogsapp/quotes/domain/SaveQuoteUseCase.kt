package com.example.dogsapp.quotes.domain

import com.example.dogsapp.dogs.di.IoDispatcher
import com.example.dogsapp.quotes.data.IQuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SaveQuoteUseCase @Inject constructor(
    private val quotesRepository: IQuotesRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ISaveQuoteUseCase {
    override suspend fun execute(quoteResponse: QuoteResponse) = withContext(defaultDispatcher){
        quotesRepository.saveQuote(quoteResponse)
    }
}