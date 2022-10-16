package com.example.dogsapp.quotes.domain

import com.example.dogsapp.quotes.data.QuotesRepository
import com.example.dogsapp.quotes.data.remote.QuoteResponse
import javax.inject.Inject



class DeleteQuoteLocallyUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
) : IDeleteQuoteLocallyUseCase {
    override suspend fun execute(quoteResponse: QuoteResponse)=
        quotesRepository.deleteQuote(quoteResponse)

}